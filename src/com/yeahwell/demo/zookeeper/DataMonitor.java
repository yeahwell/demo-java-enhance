package com.yeahwell.demo.zookeeper;

import java.util.Arrays;

import org.apache.zookeeper.AsyncCallback.StatCallback;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class DataMonitor implements Watcher, StatCallback {

	ZooKeeper zk;
	String znode;
	Watcher chainedWatcher;
	boolean dead;
	DataMonitorListener listener;
	byte prevData[];

	public DataMonitor(ZooKeeper zk, String znode, Watcher chainedWatcher,
			DataMonitorListener listener) {
		this.zk = zk;
		this.znode = znode;
		this.chainedWatcher = chainedWatcher;
		this.listener = listener;
		// Get things started by checking if the node exists. We are going to be completely event driven
		zk.exists(znode, true, this, null);
	}
	
	@Override
	public void processResult(int rc, String path, Object ctx, Stat stat) {
		boolean exists = false;
		switch(rc){
		case Code.Ok:
			exists = true;
			break;
		case Code.NoNode:
			exists = false;
			break;
		case Code.SessionExpired:
		case Code.NoAuth:
			dead = true;
			listener.closing(rc);
			return;
		default:
			//Retry errors
			zk.exists(znode, true, this, null);
			return;
		}
		
		byte b[] = null;
		if(exists){
			try{
				b = zk.getData(znode, false, null);
			}catch(KeeperException e){
				e.printStackTrace();
			}catch(InterruptedException e){
				return;
			}
		}
		if((null == b && b != prevData)
				|| (null != b && !Arrays.equals(prevData, b))){
			listener.equals(b);
			prevData = b;
		}
	}

	@Override
	public void process(WatchedEvent event) {
		String path = event.getPath();
		if (event.getType() == Event.EventType.None){
			switch(event.getState()){
			case SyncConnected:
				break;
			case Expired:
				//it's all over
				dead = true;
				listener.closing(KeeperException.Code.SessionExpired);
				break;
			}
		}else{
			if(null != path && path.equals(znode)){
				zk.exists(znode, true, this, null);
			}
		}
		if(null != chainedWatcher){
			chainedWatcher.process(event);
		}
	}
	
	 /**
     * Other classes use the DataMonitor by implementing this method
     */
    public interface DataMonitorListener {
        /**
         * The existence status of the node has changed.
         */
        void exists(byte data[]);

        /**
         * The ZooKeeper session is no longer valid.
         *
         * @param rc
         *                the ZooKeeper reason code
         */
        void closing(int rc);
    }

}
