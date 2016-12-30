package com.yeahwell.demo.jvm.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * 运行时常量池导致的内存溢出异常
 * VM args:
 * -XX:PermSize=10M -XX:MaxPermSize=10M
 * 如果要向运行时常量池中添加内容，最简单的做法就是使用String.intern()这个Native方法。
 * 该方法的作用：如果池中已经包含一个等于此String对象的字符串，则返回代表池中这个字符串的String对象；
 * 否则，将此String对象包含的字符串添加到常量池中，并且返回此String对象的引用。
 * 
 * @author yeahwell
 *
 */
public class RuntimeConstantPoolOOM {

	public static void main(String[] args) {
		//使用List保持着常量池引用，避免Full GC回收常量池行为
		List<String> list = new ArrayList<String>();
		//10MB 的PermSize在integer范围内足够产生OOM
		int i = 0;
		while(true){
			list.add(String.valueOf(i++).intern());
		}
		
	}
	
}
