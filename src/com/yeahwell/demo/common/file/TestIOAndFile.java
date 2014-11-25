package com.yeahwell.demo.common.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import org.junit.Test;

public class TestIOAndFile {

	@Test
	public void testFileReader() throws IOException {
		FileReader fileReader = new FileReader("src/火车票2.txt");
		int ch = 0;
		while ((ch = fileReader.read()) != -1) {
			System.out.print((char) ch);
		}
		fileReader.close(); // 关闭
	}

	@Test
	public void testInputStreamReader() throws IOException {
		InputStreamReader inputStreamReader = new InputStreamReader(
				new FileInputStream("src/火车票.txt"));
		int ch = 0;
		while ((ch = inputStreamReader.read()) != -1) {
			System.out.print((char) ch);
		}
		inputStreamReader.close();
	}

	@Test
	public void testBufferedStreamReader() throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(new FileInputStream("src/火车票.txt")));
		String data = null;
		while ((data = bufferedReader.readLine()) != null) {
			System.out.println(data);
		}
		bufferedReader.close();
	}

	@Test
	public void testFileWriter() throws IOException {
		FileWriter fileWriter = new FileWriter("src/hello.txt");
		String str = "Hello_____World,再加几个字";
		fileWriter.write(str, 0, str.length());
		fileWriter.close();
	}

	@Test
	public void testOutputStreamWriter() throws IOException {
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
				new FileOutputStream("src/hello2加几个中文.txt"));
		String str = "Hello_____World,再加几个字";
		outputStreamWriter.write(str, 0, str.length());
		outputStreamWriter.close();
	}

	@Test
	public void testBufferedWriter() throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream("src/hello3.txt")));
		String str = "Hello_____World,再加几个字";
		bufferedWriter.write(str, 0, str.length());
		bufferedWriter.close();
	}
	
	@Test
	public void testPrintWriter() throws IOException {
		PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream("src/hello4.txt")));  
		String str = "Hello_____World,再加几个字";
		printWriter.println(str);  
		printWriter.close();
	}
	
}
