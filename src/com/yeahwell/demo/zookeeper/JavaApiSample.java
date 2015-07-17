package com.yeahwell.demo.zookeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class JavaApiSample {

	public static final int SESSION_TIMEOUT = 10000;
	public static final String CONNECTION_STRING = "172.16.53.137:4181";
	private ZooKeeper zk = null;
	private CountDownLatch connectedSignal = new CountDownLatch(1);
	
	private Watcher wh = new Watcher() {
		public void process(WatchedEvent event) {
			Thread thread = Thread.currentThread();
			System.out.println("线程" + thread.getName() + " 正在触发watcher= " + event);
		}
	};
	
	public void connect(String host, int sessionTimeout){
		releaseConnect();
		try {
			zk = new ZooKeeper(host, sessionTimeout, wh);
			connectedSignal.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void releaseConnect(){
		if(null != zk){
			try {
				zk.close();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void createPath(String path, String data){
		try {
			System.out.println("开始createPath------");
			zk.create(path, data.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			System.out.println("结束createPath------");
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String getData(String path){
		String result = null;
		try {
			result = new String(zk.getData(path, false, null));
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean setData(String path, String data){
		boolean result = false;
		try {
			zk.setData(path, data.getBytes(), -1);
			result = true;
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
		JavaApiSample api = new JavaApiSample();
		String path = "mbp/dev/transcore_trans_web_service";
		api.connect(JavaApiSample.CONNECTION_STRING, JavaApiSample.SESSION_TIMEOUT);
		api.createPath(path, "http://192.168.1.142:8180/transcore-web/services/transactionService");
		System.out.println("修改前path的值 " + api.getData(path));
		api.setData(path, "abc");
		System.out.println("修改后path的值 " + api.getData(path));
	}
	
}
