package com.yeahwell.demo.thread.newapi.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http://www.cnblogs.com/dmir/p/4907515.html
 * @Title: testBlockQueue.java
 * @Package com.lky.test
 * @Description:多线程模拟实现生产者消费者模型(阻塞队列)
 * @author lky
 * @date 2015年10月24日 下午5:08:01
 * @version V1.0
 */
public class BlockingQueueTest3 {

    private Logger log = LoggerFactory.getLogger(BlockingQueueTest3.class);

    /**
     * @Title: testBlockQueue.java
     * @Package com.lky.test
     * @Description: 定义阻塞队列
     * @author lky
     * @date 2015年10月24日 下午5:07:28
     * @version V1.0
     */
    public class Basket {
        // 队列的最大容量为3
        BlockingQueue<String> basket = new LinkedBlockingQueue<String>(3);

        // 如果队列不满，则放入，否则阻塞等待
        public void produce(String apple) throws InterruptedException {
            basket.put(apple);
        }

        // 如果队列不为空，则取出，否则阻塞等待
        public String consumer() throws InterruptedException {
            return basket.take();
        }
    }

    /**
     * @Title: testBlockQueue.java
     * @Package com.lky.test
     * @Description: 定义生产者
     * @author lky
     * @date 2015年10月24日 下午5:18:17
     * @version V1.0
     */
    public class Produce implements Runnable {
        private Basket basket;
        private String fruit;

        public Produce(String fruit, Basket basket) {
            this.basket = basket;
            this.fruit = fruit;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    log.info("[" + Thread.currentThread().getName() + "]" + "开始生产apple----->" + this.fruit);
                    basket.produce(fruit);
                    log.info("apple生产完毕!!!!");
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                log.error("生产苹果异常!!!!!");
            }
        }
    }

    /**
     * @Title: testBlockQueue.java
     * @Package com.lky.test
     * @Description: 定义消费者
     * @author lky
     * @date 2015年10月24日 下午5:24:31
     * @version V1.0
     */
    public class Consumer implements Runnable {
        private Basket basket;

        public Consumer(Basket basket) {
            this.basket = basket;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    String fruit = basket.consumer();
                    log.info("[" + Thread.currentThread().getName() + "]" + "取到一个水果: " + fruit);
                    Thread.sleep(1000);
                }

            } catch (Exception e) {
                log.error("消费者取苹果异常!!!!");
            }
        }
    }

    @Test
    public void test() {
        System.out.println(Runtime.getRuntime().availableProcessors());//获取当前系统的cpu数目
        
        Basket basket = new Basket();
        Produce produce1 = new Produce("apple", basket);
        Produce produce2 = new Produce("banna", basket);
        Consumer consumer = new Consumer(basket);

        // 新建一个线程池
        ExecutorService service = Executors.newCachedThreadPool();

        service.submit(produce1);
        service.submit(produce2);
        service.submit(consumer);

        try {
            Thread.sleep(20000);
        } catch (Exception e) {
            log.error("程序异常错误!!!!");
        }
        service.shutdown();
    }
}