package com.yeahwell.demo.thread.c017;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
* Java�̣߳��з���ֵ���߳�
*
* @author Administrator 2009-11-5 0:41:50
*/
public class Test {
        public static void main(String[] args) throws ExecutionException, InterruptedException {
                //����һ���̳߳�
                ExecutorService pool = Executors.newFixedThreadPool(2);
                //���������з���ֵ������
                Callable c1 = new MyCallable("A");
                Callable c2 = new MyCallable("B");
                //ִ�����񲢻�ȡFuture����
                Future f1 = pool.submit(c1);
                Future f2 = pool.submit(c2);
                //��Future�����ϻ�ȡ����ķ���ֵ�������������̨
                System.out.println(">>>"+f1.get().toString());
                System.out.println(">>>"+f2.get().toString());
                //�ر��̳߳�
                pool.shutdown();
        }
}

class MyCallable implements Callable{
        private String oid;

        MyCallable(String oid) {
                this.oid = oid;
        }

        @Override
        public Object call() throws Exception {
                return oid+"���񷵻ص�����";
        }
}