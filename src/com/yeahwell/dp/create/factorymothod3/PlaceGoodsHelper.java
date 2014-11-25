package com.yeahwell.dp.create.factorymothod3;

import java.util.ArrayList;
import java.util.List;

public class PlaceGoodsHelper {

	public synchronized static void placeGoods(Goods goods){
		/**
		 * 将所有的货架工厂放置在列表中
		 * 按照尺寸从小到大依次放置
		 * 注意下面的代码使用了泛型
		 */
		List<ShelfFactory> shelfList = new ArrayList<ShelfFactory>();
		shelfList.add(new SmallShelfFactory());
		shelfList.add(new BigShelfFactory());
		
		/**
		 * 货架变量
		 */
		Shelf rightShelf;
		
		for(ShelfFactory sf : shelfList){
			/**
			 * 货架合适
			 */
			if(sf.getMaxWidth() >= goods.getWidth()){
				/**
				 * 实例化特定的货架
				 * 放置货物
				 */
				try{
					/**
					 * 使用工厂创建实例
					 */
					rightShelf = sf.createShelf();
					rightShelf.put(goods);
				//如果有合适的货架，则放置后退出方法
					return;
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		//运行到这里说明没有合适的货架
		throw new RuntimeException("没有找到符合" + goods.getName() + "尺寸的货架");
	}
}
