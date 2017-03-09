package com.yeahwell.demo.thread.common.wjt26;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class Main {
    public static void main(String[] args) {
        // 创建两个缓存对象
        List<String> buffer1 = new ArrayList<>();
        List<String> buffer2 = new ArrayList<>();

        // 创建一个交换器对象
        Exchanger<List<String>> exchanger = new Exchanger<>();

        // 创建生产者对象
        Producer producer = new Producer(exchanger, buffer1);
        // 创建消费者对象
        Consumer consumer = new Consumer(exchanger, buffer2);

        // 创建消费者对象和生产者对象放置在不同的线程中
        Thread threadProducer = new Thread(producer);
        Thread threadConsumer = new Thread(consumer);

        // 启动两个线程
        threadProducer.start();
        threadConsumer.start();
    }
}

