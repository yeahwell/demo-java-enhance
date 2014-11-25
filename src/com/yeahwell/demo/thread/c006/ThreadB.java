package com.yeahwell.demo.thread.c006;

/**
* ����1+2+3 ... +100�ĺ�
*
* @author leizhimin 2008-9-15 13:20:49
*/
public class ThreadB extends Thread {
    int total;

    public void run() {
        synchronized (this) {
            for (int i = 0; i < 101; i++) {
                total += i;
            }
            //����ɼ����ˣ������ڴ˶���������ϵȴ�ĵ����̣߳��ڱ������߳�A������
            notify();
        }
    }
    
}