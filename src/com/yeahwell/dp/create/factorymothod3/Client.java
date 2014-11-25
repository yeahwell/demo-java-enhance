package com.yeahwell.dp.create.factorymothod3;

import java.util.ArrayList;
import java.util.List;

public class Client {

	public static void main(String[] args) {
		
		/**
		 * 初始化一些商品放置于列表中
		 */
		List<Goods> goodsList = new ArrayList<Goods>();
		
		goodsList.add(new TV());
		goodsList.add(new Phone());
		goodsList.add(new Bed());
		
		System.out.println("初始化货物列表完成 ！ ");
		
		/**
		 * 放置商品
		 */
		for(Goods goods : goodsList){
			try{
				PlaceGoodsHelper.placeGoods(goods);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		System.out.println("放置货物完成");
	}
}
