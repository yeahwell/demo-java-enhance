package com.yeahwell.dp.create.factorymothod.before;


/**
 * 测试类
 * @author yeahwell
 *
 */
public class SampleShelfClient {

	public static void main(String[] args) {
		
		//得到商品
	    Goods goods1 = new Goods("矿泉水");
	    SampleShelf sampleShelf1 = new SampleShelf();
	    sampleShelf1.put(goods1);
	    
	    Goods goods2 = new Goods("面包");
	    SampleShelf sampleShelf2 = new SampleShelf();
	    sampleShelf2.put(goods2);
		
	}
}
