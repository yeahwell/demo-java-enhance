package com.yeahwell.demo.zookeeper.zkclient;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.apache.helix.manager.zk.ZkClient;
import org.apache.zookeeper.Watcher.Event.KeeperState;

public class ZkClientSample {

	public static String prefix(){
		Thread current = Thread.currentThread();
		return current.getName();
	}
	
	public static void testzkClient(final String serverList) {
		
		String PATH = "/testRootPath";
		
		// 1. 订阅children变化
		ZkClient zkClient4subChild = new ZkClient(serverList);
		zkClient4subChild.subscribeChildChanges(PATH, new IZkChildListener() {
			@Override
			public void handleChildChange(String parentPath, List currentChilds)
					throws Exception {
				System.out.println(prefix() + "clildren of path " + parentPath
						+ ":" + currentChilds);
			}
		});
		
		//2. 订阅数据变化
		ZkClient zkClient4subData = new ZkClient(serverList);
		zkClient4subData.subscribeDataChanges(PATH, new IZkDataListener() {
			@Override
			public void handleDataChange(String dataPath, Object data)
					throws Exception {
				System.out.println(prefix() + "Data of " + dataPath
						+ " has changed");
			}
			@Override
			public void handleDataDeleted(String dataPath) throws Exception {
				System.out.println(prefix() + dataPath + " has deleted");
			}
		});
		
		//订阅状态变化
		ZkClient zkClient4subStat = new ZkClient(serverList);
		zkClient4subStat.subscribeStateChanges(new IZkStateListener() {
			@Override
			public void handleNewSession() throws Exception {
				System.out.println(prefix() + "handleNewSession()");
			}

			@Override
			public void handleStateChanged(KeeperState stat) throws Exception {
				System.out.println(prefix() + "handleStateChanged,stat:" + stat);
			}

			@Override
			public void handleSessionEstablishmentError(Throwable arg0)
					throws Exception {
				// TODO Auto-generated method stub
			}
		});
	}
	
	public static void main(String[] args) {
		testzkClient("172.16.53.137:4181");
		try {
			Thread.sleep(80000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("exc done......");
		
	}

}
