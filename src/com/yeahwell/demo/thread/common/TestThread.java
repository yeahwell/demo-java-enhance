package com.yeahwell.demo.thread.common;

public class TestThread extends Thread{
	
	public TestThread(String name){
		super(name);
	}

	@Override
	public void run(){
        for(int i = 0;i<5;i++){ 
            for(long k= 0; k <100000000;k++); 
            System.out.println(this.getName()+" :"+i); 
        } 
	}
	
	public static void main(String[] args) {
        Thread t1 = new TestThread("跑步"); 
        Thread t2 = new TestThread("游泳"); 
        t1.start(); 
        t2.start(); 
	}
}
