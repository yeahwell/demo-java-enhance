package com.yeahwell.demo.thread.c004;

public class JoinThread extends Thread{
    public JoinThread(String name){
          super(name);
    }
    
    public void run(){
          for(int i=0; i<10; i++){
                for(long k=0; k<100000000; k++){}
                System.out.println(this.getName() + ": " +  i);
          }
    }
    
    public static void main(String[] args){
          Thread t1 = new JoinThread("�߳�A");
          t1.start();
//          Thread t2 = new JoinThread("�߳�B");
          try{
                t1.join(1000);                 //Join������~
          } catch(InterruptedException e) {
                e.printStackTrace();
          }      
          System.out.println("over");
    }
}
