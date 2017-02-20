package com.yeahwell.demo.thread.dispatch;

public class ThreadJoin2 extends Thread{
    public ThreadJoin2(String name){
          super(name);
    }
    
    public void run(){
          for(int i=0; i<10; i++){
                for(long k=0; k<100000000; k++){}
                System.out.println(this.getName() + ": " +  i);
          }
    }
    
    public static void main(String[] args){
          Thread t1 = new ThreadJoin2("ThreadJoin2");
          t1.start();
          try{
                t1.join(1000);               
          } catch(InterruptedException e) {
                e.printStackTrace();
          }      
          System.out.println("over");
    }
}
