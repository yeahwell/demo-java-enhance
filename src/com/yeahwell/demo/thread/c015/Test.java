package com.yeahwell.demo.thread.c015;

/**
* Java�̣߳�����Э��-����
*
* @author Administrator 2009-11-4 22:06:13
*/
public class Test {
        public static void main(String[] args) {
                DeadlockRisk dead = new DeadlockRisk();
                MyThread t1 = new MyThread(dead, 1, 2);
                MyThread t2 = new MyThread(dead, 3, 4);
                MyThread t3 = new MyThread(dead, 5, 6);
                MyThread t4 = new MyThread(dead, 7, 8);

                t1.start();
                t2.start();
                t3.start();
                t4.start();
        }

}

class MyThread extends Thread {
        private DeadlockRisk dead;
        private int a, b;


        MyThread(DeadlockRisk dead, int a, int b) {
                this.dead = dead;
                this.a = a;
                this.b = b;
        }

        @Override
        public void run() {
                dead.read();
                dead.write(a, b);
        }
}

class DeadlockRisk {
        private static class Resource {
                public int value;
        }

        private Resource resourceA = new Resource();
        private Resource resourceB = new Resource();

        public int read() {
                synchronized (resourceA) {
                        System.out.println("read():" + Thread.currentThread().getName() + "��ȡ��resourceA����");
                        synchronized (resourceB) {
                                System.out.println("read():" + Thread.currentThread().getName() + "��ȡ��resourceB����");
                                return resourceB.value + resourceA.value;
                        }
                }
        }

        public void write(int a, int b) {
                synchronized (resourceB) {
                        System.out.println("write():" + Thread.currentThread().getName() + "��ȡ��resourceA����");
                        synchronized (resourceA) {
                                System.out.println("write():" + Thread.currentThread().getName() + "��ȡ��resourceB����");
                                resourceA.value = a;
                                resourceB.value = b;
                        }
                }
        }
}