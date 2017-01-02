package com.yeahwell.demo.jvm.jmm;

/**
 * 我们创建了4个线程来对long类型的变量t进行赋值，赋值分别为100,200，-300，-400，有一个线程负责读取变量t,如果正常的话，读取到的t的值应该是我们赋值中的一个，但是在32的JVM中，事情会出乎预料。如果程序正常的话，我们控制台不会有任何的输出，可实际上，程序一运行，控制台就输出了下面的信息：

-4294967096
4294966896
-4294967096
-4294967096
4294966896
之所以会出现上面的情况，是因为在32位JVM中，64位的long数据的读和写都不是原子操作，即不具有原子性，并发的时候相互干扰了。

　　32位的JVM中，要想保证对long、double类型数据的操作的原子性，可以对访问该数据的方法进行同步，就像下面的：
 * @author yeahwell
 *
 */
public class Atomicity {
    //静态变量t
    public  static long t = 0;
    //静态变量t的get方法,同步方法
    public synchronized static long getT() {
        return t;
    }
    //静态变量t的set方法，同步方法
    public synchronized static void setT(long t) {
        Atomicity.t = t;
    }
    //改变变量t的线程
    public static class ChangeT implements Runnable{
        private long to;
        public ChangeT(long to) {
            this.to = to;
        }
        public void run() {
            //不断的将long变量设值到 t中
            while (true) {
                Atomicity.setT(to);
                //将当前线程的执行时间片段让出去，以便由线程调度机制重新决定哪个线程可以执行
                Thread.yield();
            }
        }
    }
    //读取变量t的线程，若读取的值和设置的值不一致，说明变量t的数据被破坏了，即线程不安全
    public static class ReadT implements Runnable{

        public void run() {
            //不断的读取NotAtomicity的t的值
            while (true) {
                long tmp = Atomicity.getT();
                //比较是否是自己设值的其中一个
                if (tmp != 100L && tmp != 200L && tmp != -300L && tmp != -400L) {
                    //程序若执行到这里，说明long类型变量t，其数据已经被破坏了
                    System.out.println(tmp);
                }
                ////将当前线程的执行时间片段让出去，以便由线程调度机制重新决定哪个线程可以执行
                Thread.yield();
            }
        }
    }
    public static void main(String[] args) {
        new Thread(new ChangeT(100L)).start();
        new Thread(new ChangeT(200L)).start();
        new Thread(new ChangeT(-300L)).start();
        new Thread(new ChangeT(-400L)).start();
        new Thread(new ReadT()).start();
    }
}