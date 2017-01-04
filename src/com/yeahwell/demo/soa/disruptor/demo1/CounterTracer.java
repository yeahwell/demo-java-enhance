package com.yeahwell.demo.soa.disruptor.demo1;

public interface CounterTracer {

    public void start();
    
    public long getMilliTimeSpan();
    
    public boolean count();
    
    public void waitForReached() throws InterruptedException;
}
