package com.yeahwell.demo.bytes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteOrder;

public class TestCPU {
	
	public static void testByJava(){
		if(ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN){
			System.out.println("BIG_ENDIAN");
		}else{
			System.out.println("LITTLE_ENDIAN");
		}
	}
	
	public static void testByInt() throws IOException{
		byte[] byteAr = new byte[]{0x78,0x56,0x34,0x12};
		ByteArrayInputStream bais = new ByteArrayInputStream(byteAr);
		DataInputStream dis = new DataInputStream(bais);
		System.out.println(Integer.toHexString(dis.readInt()));
	}
	
	public static void testByByte() throws IOException{
		/** 
         * 将整形(int)转为字节数组（byte[]） 
         */  
        int a = 0x12345678;
        //一般PC都是小端储存
        //Java虚拟机使用big-endian, Intel x86使用 little-endian
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        DataOutputStream dos = new DataOutputStream(baos);
        //Writes an int to the underlying output stream as four bytes, high byte first.
        dos.writeInt(a);  
        byte[] b = baos.toByteArray();  
        for(int i = 3; i >= 0; i--)  
        {  
        	//System.out.println("b[i]=" + b[i]);
            System.out.println(Integer.toHexString(b[i]));  
        }  
        System.out.println();  

	}

	public static void main(String[] args) throws Exception {
		testByJava();
		testByByte();
		testByInt();
	}
}
