package com.yeahwell.demo.j2se.core.collect.hashmap;

import java.util.HashMap;
import java.util.Iterator;

/**
 *  Java的HashMap工作原理
 * 	HashMap有一个叫做Entry的内部类，它用来存储key-value对。
	上面的Entry对象是存储在一个叫做table的Entry数组中。
	table的索引在逻辑上叫做“桶”(bucket)，它存储了链表的第一个元素。
	key的hashcode()方法用来找到Entry对象所在的桶。
	如果两个key有相同的hash值，他们会被放在table数组的同一个桶里面。
	key的equals()方法用来确保key的唯一性。
	value对象的equals()和hashcode()方法根本一点用也没有。
 *
 */
public class HashMapStructure {
	/**
	 * @author Arpit Mandliya
	 */
	public static void main(String[] args) {

		Country india = new Country("India", 1000);
		Country japan = new Country("Japan", 10000);

		Country france = new Country("France", 2000);
		Country russia = new Country("Russia", 20000);

		HashMap<Country, String> countryCapitalMap = new HashMap<Country, String>();
		countryCapitalMap.put(india, "Delhi");
		countryCapitalMap.put(japan, "Tokyo");
		countryCapitalMap.put(france, "Paris");
		countryCapitalMap.put(russia, "Moscow");

		Iterator<Country> countryCapitalIter = countryCapitalMap.keySet()
				.iterator();// put debug point at this line
		while (countryCapitalIter.hasNext()) {
			Country countryObj = countryCapitalIter.next();
			String capital = countryCapitalMap.get(countryObj);
			System.out.println(countryObj.getName() + "----" + capital);
		}
	}

}
