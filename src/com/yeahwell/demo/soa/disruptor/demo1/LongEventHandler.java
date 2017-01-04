package com.yeahwell.demo.soa.disruptor.demo1;

import com.lmax.disruptor.EventHandler;

/**
 * 通过实现接口 com.lmax.disruptor.EventHandler<T> 定义事件处理的具体实现。
 * @author yeahwell
 *
 */
public class LongEventHandler implements EventHandler<LongEvent>
{
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch)
    {
        System.out.println("Event: " + event);
    }
}