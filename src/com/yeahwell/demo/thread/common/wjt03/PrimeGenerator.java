package com.yeahwell.demo.thread.common.wjt03;

/**
 * Thread类有一个表明线程被中断与否的属性，它存放的是布尔值。线程的interrupt() 方法被调用时，这个属性就会被设置为true。 
　　isInterrupted()方法只是返回这个属性的值。还有一个方法可以检査线程是否已被中断，即Thread类的静态方法interrupted(),用 来检査当前执行的线程是否被中断。 
　　isInterrupted()和 interrupted()方法有一个很大的区别。isInterrupted()不能改变 interrupted 属性的值，但是后者能设置interrupted属性为false。因为interrupted()是一个静态方法，更推荐使用isInterruptedO()方法。 
线程可以忽略中断，但并不是预期的行为。
 * @author yeahwell
 *
 */
public class PrimeGenerator extends Thread {

	@Override
	public void run() {
		long number = 1L;

		while (true) {
			// 对每个数字，计算它是不是一个质数，如果是的话就打印到控制台。
			if (isPrime(number)) {
				System.out.printf("Number %d is Prime\n", number);
			}

			// 当被中断时，输出一条消息，并且退出方法
			if (isInterrupted()) {
				System.out.printf("The Prime Generator has been Interrupted\n");
				return;
			}
			number++;
		}
	}

	/**
	 * 判断一个数是否是质数
	 *
	 * @param number
	 *            待判断的数
	 * @return true是质数，false不是质数
	 */
	private boolean isPrime(long number) {
		if (number <= 2) {
			return true;
		}

		for (long i = 2; i < number; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}
}
