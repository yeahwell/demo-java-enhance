package com.yeahwell.demo.j2se.core.io.file;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class FileDemo {

	private static void showFileInfomation(File file){
		//显示读写信息
		System.out.println("是否可读: " + file.canRead());
		System.out.println("是否可写: " + file.canWrite());
		System.out.println("文件大小: " + file.length());
		System.out.println("最后修改时间: " + new Date(file.lastModified()));
		
		//显示路径信息
		System.out.println("绝对路径为: " + file.getAbsolutePath());
		System.out.println("文件名为: " + file.getName()); 
		System.out.println("所在目录为: " + file.getParent());
	}
	
	private static void createNewFile(String filePath) throws IOException{
		File file = new File(filePath);
		if(!file.exists()){
			if(file.createNewFile()){
				System.out.println("文件创建成功");
			}else{
				System.out.println("文件创建失败");
			}
		}else{
			System.out.println("文件已存在");
		}
	}
	
	private static void deleteFile(String filePath){
		File file = new File(filePath);
		if(file.exists()){
			System.out.println("文件存在");
		}else{
			System.out.println("文件不存在");
		}
		
		if(file.delete()){
			System.out.println("文件被删除了");
		}else{
			System.out.println("无法删除文件");
		}
		
		if(file.exists()){
			System.out.println("文件存在");
		}else{
			System.out.println("文件不存在");
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println(File.separator);
		System.out.println(System.getProperty("file.separator"));
		
		File file = new File("src/com/yeahwell/demo/j2se/core/io/file/FileDemo.java");
		if(file.isFile()){
			System.out.println(file + "是一个文件");
		}
		
		showFileInfomation(file);
		
		createNewFile("NewFile.txt");
		
		deleteFile("NewFile.txt");
	}
	
}
