package com.yeahwell.demo.zookeeper.locks;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/***
 * 基于zookeeper实现的 分布式公平锁
 * 
 * @author qin dong liang QQ技术群交流：324714439
 * 
 * */
public class Lock1 implements Watcher {

	/**
	 * ZK实例
	 * */
	private ZooKeeper zk;

	/** 原子计数锁，防止在zk没有连上前，执行CURD操作 */
	private CountDownLatch down = new CountDownLatch(1);

	public Lock1() {
		// TODO Auto-generated constructor stub
	}

	public Lock1(String host) throws Exception {
		this.zk = new ZooKeeper(host, 5000, new Watcher() {

			@Override
			public void process(WatchedEvent event) {
				// TODO Auto-generated method stub
				/** 链接上zk服务，岂可取消阻塞计数 **/
				if (event.getState() == KeeperState.SyncConnected) {
					down.countDown();
				}

			}
		});
	}

	/**
	 * 字符编码
	 * 
	 * **/
	private static final Charset CHARSET = Charset.defaultCharset();

	/***
	 * 
	 * 此方法是写入数据 如果不存在此节点 就会新建，已存在就是 更新
	 * 
	 * **/
	public void write(String path, String value) throws Exception {

		Stat stat = zk.exists(path, false);
		if (stat == null) {
			zk.create(path, value.getBytes(CHARSET), Ids.OPEN_ACL_UNSAFE,
					CreateMode.PERSISTENT);
		} else {
			zk.setData(path, value.getBytes(CHARSET), -1);
		}

	}

	/**
	 * 
	 * 切换锁
	 * 
	 * **/
	public void check() throws Exception {
		List<String> list = zk.getChildren("/a", null);
		Collections.sort(list);// 排序使得节点有次序
		if (list.isEmpty()) {
			System.out.println("此父路径下面没有节点,分布式锁任务完成或还没启动！");
		} else {
			String start = list.get(0);// 获取第一个节点
			String data = new String(zk.getData("/a/" + start, false, null));
			if (data.equals("a")) {// 等于本身就启动作为Master

				if (list.size() == 1) {
					startMaster();// 作为Master启动
				} else {
					automicSwitch();// 对于非第一个启动的节点，会调用此方法，因为他的第一个挂了
					// 或释放锁了，所以它是抢占的
				}
			} else {
				// 非当前节点，就打印当前节点，监控的节点
				for (int i = 0; i < list.size(); i++) {
					// 获取那个节点存的此客户端的模拟IP
					String temp = new String(zk.getData("/a/" + list.get(i),
							false, null));
					if (temp.equals("a")) {
						// 因为前面作为首位判断，所以这个出现的位置不可能是首位
						// 需要监听小节点里面的最大的一个节点
						String watchPath = list.get(i - 1);
						System.out.println("Lock1监听的是:  " + watchPath);

						zk.exists("/a/" + watchPath, this);// 监听此节点的详细情况，如果发生节点注销事件
						// 则会触发自身的process方法
						break;// 结束循环
					}

				}

			}

		}

	}

	@Override
	public void process(WatchedEvent event) {
		if (event.getType() == Event.EventType.NodeDeleted) {
			// 如果发现，监听的节点，挂掉了，那么就重新，进行监听
			try {
				System.out.println("注意有锁退出或释放，公平锁开始抢占........");
				check();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * 读取数据，给定一个路径和 监听事件
	 * 
	 * ***/
	public String read(String path, Watcher watch) throws Exception {
		byte[] data = zk.getData(path, watch, null);
		return new String(data, CHARSET);
	}

	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 关闭zk连接
	 * 
	 * **/
	public void close() throws Exception {
		zk.close();
	}

	/**
	 * 释放锁
	 * 
	 * @throws Exception
	 */
	public void automicSwitch() throws Exception {

		// System.out.println("有节点释放锁，Lock1锁占入.......,  时间  "+f.format(new
		// Date()));
		System.out.println("Lock1的上级锁节点退出或释放锁了，Lock1锁占入.......,  时间  "
				+ f.format(new Date()));
	}

	/**
	 * 创建一个持久node，
	 * 
	 * **/
	public void createPersist() throws Exception {

		zk.create("/a", "主节点".getBytes(), Ids.OPEN_ACL_UNSAFE,
				CreateMode.PERSISTENT);

		System.out.println("创建主节点成功........");

	}

	/***
	 * 创建锁node，注意是抢占的
	 * 
	 * 
	 * */
	public void createTemp() throws Exception {
		zk.create("/a/b", "a".getBytes(), Ids.OPEN_ACL_UNSAFE,
				CreateMode.EPHEMERAL_SEQUENTIAL);
		System.out.println("Lock1注册锁成功，进入公平队列...........");

	}

	public static void main(String[] args) throws Exception {

		// Slave s=new Slave("192.168.120.128:2181");
		Lock1 lock = new Lock1("172.16.53.146:3181");
		// lock.createPersist();//创建主节点
		lock.createTemp();// 注册临时有序节点
		lock.check();
		Thread.sleep(Long.MAX_VALUE);
		// lock.close();

	}

	/***
	 * 获取锁成功
	 * 
	 * */
	public void startMaster() {

		System.out.println("Lock1节点获取锁了，其他节点等待........");
	}

}