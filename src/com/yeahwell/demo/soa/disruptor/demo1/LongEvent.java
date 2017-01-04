package com.yeahwell.demo.soa.disruptor.demo1;

/**
 * 事件(Event)就是通过 Disruptor 进行交换的数据类型。
 * @author yeahwell
 *
 */
public class LongEvent {

	private long value;

	public void set(long value) {
		this.value = value;
	}
	
}
