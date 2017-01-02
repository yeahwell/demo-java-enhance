package com.yeahwell.demo.j2se.core.io.read;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestThreadIO {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TestThreadIO().test();
	}

	private void test() {
		ReadThead rt = new ReadThead();
		Thread t1 = new Thread(rt, "ReadThead");
		WriteThead wt = new WriteThead();
		Thread t2 = new Thread(wt, "WriteThead");
		t1.start();
		t2.start();
	}

	protected class WriteThead implements Runnable {

		@Override
		public void run() {
			try {
				File f = new File("E:\\a.txt");
				FileOutputStream fos = new FileOutputStream(f);
				byte[] b = "hello".getBytes();
				while (true) {
					fos.write(b);
					fos.flush();
					Thread.sleep(1000);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	protected class ReadThead implements Runnable {

		@Override
		public void run() {
			try {
				File f = new File("E:\\a.txt");
				FileInputStream fis = new FileInputStream(f);
				int readData;
				while (true) {
					readData = fis.read();
					if (readData == -1) {
						Thread.sleep(1000);
					} else {
						System.out.println(readData);
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
