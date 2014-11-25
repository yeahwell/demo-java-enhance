package com.yeahwell.demo.thread.c016;

public class MyThread extends Thread{
    @Override
    public void run() {
            System.out.println(Thread.currentThread().getName()+"����ִ�С�����");
    }
}