package com.yeahwell.demo.thread.c003;

/** 
* һ��������������100����ÿ������֮����ͣ1�룬ÿ��10���������һ���ַ� 
* 
* @author leizhimin 2008-9-14 9:53:49 
*/ 
public class MyThread extends Thread { 

    public void run() { 
        for (int i = 0; i < 100; i++) { 
            if ((i) % 10 == 0) { 
                System.out.println("-------" + i); 
            } 
            System.out.print(i); 
            try { 
                Thread.sleep(1); 
                System.out.print("    �߳�˯��1���룡\n"); 
            } catch (InterruptedException e) { 
                e.printStackTrace(); 
            } 
        } 
    } 

    public static void main(String[] args) { 
        new MyThread().start(); 
    } 
} 
