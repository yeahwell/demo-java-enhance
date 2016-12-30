package com.yeahwell.demo.jvm.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * <h5>Java 堆内存溢出</h5>
 * VM arguments 
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * 限制Jva堆的大小为20M，不可拓展
 * -XX:+HeapDumpOnOutOfMemoryError
 * @author yeahwell
 *
 */
public class HeapOOM {

	static class OOMObject{
		
	}
	
	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<OOMObject>();
		while(true){
			list.add(new OOMObject());
		}
	}
	
}
