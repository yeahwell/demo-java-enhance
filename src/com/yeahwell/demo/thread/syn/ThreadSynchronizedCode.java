package com.yeahwell.demo.thread.syn;

/**
 * 线程同步方法
 * 
 */
public class ThreadSynchronizedCode {
    public static void main(String[] args) {
        ThreadSynchronizedCode t = new ThreadSynchronizedCode();
        User u = t.new User("张三", 100);
        MyThread t1 = t.new MyThread("线程A", u, 20);
        MyThread t2 = t.new MyThread("线程B", u, -60);
        MyThread t3 = t.new MyThread("线程C", u, -80);
        MyThread t4 = t.new MyThread("线程D", u, -30);
        MyThread t5 = t.new MyThread("线程E", u, 32);
        MyThread t6 = t.new MyThread("线程F", u, 21);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }

    class MyThread extends Thread {
        private User u;
        /**存款金额*/
        private int y = 0;
        
        MyThread(String name, User u, int y) {
            super(name);
            this.u = u;
            this.y = y;
        }

        public void run() {
            u.oper(y);
        }
    }

    class User {
        /** 账号 */
        private String code;
        /** 余额 */
        private int cash;

        User(String code, int cash) {
            this.code = code;
            this.cash = cash;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        /**
         * 存款
         * 
         * @param x 欲存款金额
         *            
         */
        public void oper(int x) {
            try {
                Thread.sleep(10L);
                synchronized (this) {
                    this.cash += x;
                    System.out.println("线程" + Thread.currentThread().getName() + "运行结束，增加“" + x
                            + "”，当前用户账户余额为：" + cash);
                }
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "User{" + "code='" + code + '\'' + ", cash=" + cash + '}';
        }
    }
}