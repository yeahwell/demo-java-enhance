package com.yeahwell.demo.thread.newapi;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountdownLatchTest {
	
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		// 裁判发布命令的计数器，计数器为0，运动员就跑
		final CountDownLatch cdOrder = new CountDownLatch(1);
		// 运动员跑到终点的计数器，为0裁判宣布结果
		final CountDownLatch cdAnswer = new CountDownLatch(3);
		// 产生3个运动员
		for (int i = 0; i < 3; i++) {
			Runnable runnable = new Runnable() {
				public void run() {
					try {
						System.out.println("线程"
								+ Thread.currentThread().getName() + "正准备接受命令");
						cdOrder.await();
						System.out.println("线程"
								+ Thread.currentThread().getName() + "已接受命令");
						Thread.sleep((long) (Math.random() * 10000));
						System.out
								.println("线程"
										+ Thread.currentThread().getName()
										+ "回应命令处理结果");
						cdAnswer.countDown();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			service.execute(runnable); // 运动员开始任务
		}
		try {
			// 裁判休息一会 再发布命令
			Thread.sleep((long) (Math.random() * 10000));
			System.out.println("线程" + Thread.currentThread().getName()
					+ "即将发布命令");
			cdOrder.countDown(); // 命令计数器置为0，发布命令
			System.out.println("线程" + Thread.currentThread().getName()
					+ "已发送命令，正在等待结果");
			cdAnswer.await(); // 等待所有运动员，计数器为0 所有运动员到位
			System.out.println("线程" + Thread.currentThread().getName()
					+ "已收到所有响应结果");
		} catch (Exception e) {
			e.printStackTrace();
		}
		service.shutdown();
	}
}
