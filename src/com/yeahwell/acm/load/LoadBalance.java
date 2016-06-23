package com.yeahwell.acm.load;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoadBalance {

	/**
	 * 锁对象
	 */
	private static Object locker = new Object();
	
	/**
	 * 服务器权重列表
	 */
	private static List<Integer> weightList = new ArrayList<Integer>();
	
	/**
	 * 当前索引
	 */
	private static int currentIndex;
	
	/**
	 * 当前权重
	 */
	private static int currentWeight;
	
	/**
	 * 最大权重
	 */
	private static int maxWeight;
	
	private static int gcd;
	
	static {
		currentIndex = -1;
		currentWeight = 0;
		
		//获取服务器权重列表，从配置文件
		weightList = getWeightList();
		maxWeight = getMaxWeight(weightList);
		gcd = getMaxGCD(weightList);
	}
	
	private static List<Integer> getWeightList(){
		List<Integer> list = new ArrayList<Integer>();
		list.add(3);
		list.add(1);
		list.add(1);
		list.add(4);
		list.add(1);
		list.add(7);
		return list;
	}
	
	/**
	 * 获取服务器权重集合中的最大权重
	 * @param list
	 * @return
	 */
	private static int getMaxWeight(List<Integer> list){
		int iMaxWeight = 0;
		for(Integer i : list){
			if(iMaxWeight < i){
				iMaxWeight = i;
			}
		}
		return iMaxWeight;
	}
	
	/**
	 * 获取最大公约数
	 * 
	 * @param list
	 * @return
	 */
	private static int getMaxGCD(List<Integer> list){
		Collections.sort(list);
		
		int iMinWeight =  weightList.get(0);
		int gcd = 1;
		for(int i = 1; i < iMinWeight; i++){
			boolean isFound = true;
			for(Integer weight : list){
				if(weight % i != 0){
					isFound = false;
					break;
				}
			}
			if(isFound){
				gcd = i;
			}
		}
		return gcd;
	}
	
	public static Integer start(){
		synchronized (locker) {
			Integer iWeigth = roundRobin();
			if(iWeigth != null){
				return iWeigth;
			}
			return weightList.get(0);
		}
	}
	
	private static Integer roundRobin(){
		while(true){
			currentIndex = (currentIndex + 1) % weightList.size();
			
			if(0 == currentIndex){
				currentWeight = currentWeight - gcd;
				if(0  >= currentWeight){
					currentWeight = maxWeight;
					if(currentWeight == 0){
						return null;
					}
				}
			}
			
			if(weightList.get(currentIndex) >= currentWeight){
				return weightList.get(currentIndex);
			}
		}
	}
}
