package com.yeahwell.demo.bit;

import java.util.BitSet;

import org.junit.Test;

public class TestBitSet {

	public static void echo(Object str) {
		System.out.println(String.valueOf(str));
	}

	/**
	 * echo no change line
	 * 
	 * @param str
	 */
	public static void echonl(String str) {
		System.out.print(String.valueOf(str));
	}

	@Test
	public void test1() {
		/*
		 * 说明默认的构造函数声明一个64位的BitSet，值都是false。 如果你要用的位超过了默认size,它会再申请64位，而不是报错。
		 */
		BitSet bs = new BitSet();
		echo(bs.isEmpty() + "\t" + bs.size());
		bs.set(0);
		echo(bs.isEmpty() + "\t" + bs.size());
		bs.set(1);
		echo(bs.get(65));
		echo(bs.isEmpty() + "\t" + bs.size());
		bs.set(65);
		echo(bs.isEmpty() + "\t" + bs.size());
	}

	@Test
	public void test2() {
		BitSet bm1 = new BitSet(7);
		System.out.println(bm1.isEmpty() + "--" + bm1.size());

		BitSet bm2 = new BitSet(63);
		System.out.println(bm2.isEmpty() + "--" + bm2.size());

		BitSet bm3 = new BitSet(65);
		System.out.println(bm3.isEmpty() + "--" + bm3.size());

		BitSet bm4 = new BitSet(111);
		System.out.println(bm4.isEmpty() + "--" + bm4.size());
	}

	@Test
	public void test3() {
		int[] numArray = { 2, 42, 5, 6, 6, 18, 33, 15, 25, 31, 28, 37 };
		
		BitSet bs = new BitSet(getMaxValue(numArray));
		echo(bs.size());
		
		putValueIntoBitSet(numArray, bs);
		
		printBitSet(bs);
	}
	
	  //打印  
    public static void printBitSet(BitSet bs){
        StringBuffer buf=new StringBuffer();  
        buf.append("[\n");  
        for(int i=0;i<bs.size();i++){  
            if(i<bs.size()-1){  
                buf.append(getBitTo10(bs.get(i))+",");  
            }else{  
                buf.append(getBitTo10(bs.get(i)));  
            }  
            if((i+1)%8==0&&i!=0){  
                buf.append("\n");  
            }  
        }  
        buf.append("]");  
        System.out.println(buf.toString());  
    }  
    
    //true,false换成1,0为了好看  
    public static String getBitTo10(boolean flag){  
        String a="";  
        if(flag==true){  
            return "1";  
        }else{  
            return "0";  
        }  
    }  
  

	// 找出数据集合最大值
	public static int getMaxValue(int[] numArray) {
		int temp = 0;
		temp = numArray[0];
		for (int i = 0; i < numArray.length; i++) {
			if (temp < numArray[i]) {
				temp = numArray[i];
			}
		}
		echo("maxvalue:" + temp);
		return temp;
	}

	// 放值
	public static void putValueIntoBitSet(int[] shu, BitSet bs) {
		for (int i = 0; i < shu.length; i++) {
			bs.set(shu[i], true);
		}
	}

}
