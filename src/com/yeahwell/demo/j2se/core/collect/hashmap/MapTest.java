package com.yeahwell.demo.j2se.core.collect.hashmap;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 从运行结果可以很明显的看出这TreeMap和LinkedHashMap的区别，前者是按字符串排序进行输出的，而后者是根据插入顺序进行输出的。
 * 细心的读者可以发现，HashMap与TreeMap的区别，与之前提到的HashSet与TreeSet的区别是一致的，在后续进行源码分析的时候，我们可以看到HashSet和TreeSet本质上分别是通过HashMap和TreeMap来实现的，所以它们的区别自然也是相同的。
 * HashTable现在已经很少使用了，与HashMap的主要区别是HashTable是线程安全的，不过由于其效率比较低，所以通常使用HashMap，在多线程环境下，通常用ConcurrentHashMap来代替。
 * @author yeahwell
 *
 */
public class MapTest {
	public static void main(String[] args) {
		Map<String, String> treeMap = new TreeMap<String, String>();
		Map<String, String> linkedMap = new LinkedHashMap<String, String>();

		treeMap.put("b", null);
		treeMap.put("c", null);
		treeMap.put("a", null);

		for (Iterator<String> iter = treeMap.keySet().iterator(); iter
				.hasNext();) {
			System.out.println("TreeMap=" + iter.next());
		}

		System.out.println("----------分割线---------");

		linkedMap.put("b", null);
		linkedMap.put("c", null);
		linkedMap.put("a", null);

		for (Iterator<String> iter = linkedMap.keySet().iterator(); iter
				.hasNext();) {
			System.out.println("LinkedHashMap=" + iter.next());
		}
	}
}