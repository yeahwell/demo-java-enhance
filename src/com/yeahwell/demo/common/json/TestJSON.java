package com.yeahwell.demo.common.json;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;
import org.junit.Test;

/**
 * 1在Java中JSON格式的String最好用单引号表示;
 * 
 * 2.利用JSONObject+JSONTokener能够读取JSON格式文件对象;
 * 
 * 3.利用PrintWriter+JSONStringer可以写入JSON文件;
 * 
 * 注：由于原本想要试图用JSONWriter写入，但是没有成功；所以只能利用JSONStringer+PrintWriter写入;
 * 
 */
public class TestJSON {

	@Test
	public void testJSONObject() throws JSONException {
		JSONObject jsonObject = new JSONObject("{'name':'xiazdong','age':20}");
		System.out.println(jsonObject.toString());
		String name = jsonObject.getString("name");
		int age = jsonObject.getInt("age");
		System.out.println(name + ":" + age);
	}

	@Test
	public void testJSONArray() throws JSONException {
		JSONArray jsonArray = new JSONArray(
				"[{'name':'lilei','age':20},{'name':'hanmeimei','age':15}]");
		System.out.println(jsonArray.toString());
		for (int i = 0; i < jsonArray.length(); i++) {
			String name = jsonArray.getJSONObject(i).getString("name");
			int age = jsonArray.getJSONObject(i).getInt("age");
			System.out.println("name=" + name);
			System.out.println("age=" + age);
		}
	}

	@Test
	public void testJSONObjectAndJSONArray() throws JSONException {
		String str = "{'name':'lilei','age':20,'book':['book1','book2']}";
		JSONObject obj = new JSONObject(str);
		System.out.println(obj.getJSONArray("book").getString(0));
	}

	/*
	 * JSONStringer可以用来快速构建一个JSON格式的文本，并转换成String，可以写入文件；
	 * JSONStringer是JSONWriter的子类;
	 * JSONStringer一般通过object().key().value().key().value().endObject()进行构造;
	 * object()表明开始一个对象，即添加{ ; endObject()表明结束一个对象，即添加 }; array()表明开始一个数组,即添加一个
	 * [ ; endArray()表明结束一个数组，即添加一个 ] ; key()表示添加一个key; value()表示添加一个value;
	 */
	@Test
	public void testJSONStringer() throws JSONException {
		JSONStringer stringer = new JSONStringer();
		String str = stringer.object().key("name").value("xiazdong").key("age")
				.value(20).endObject().toString();
		System.out.println(str);
	}

	@Test
	public void testComplexJson() throws JSONException, IOException {
		JSONStringer jsonStringer = new JSONStringer();
		JSONObject obj2 = new JSONObject();
		JSONObject obj3 = new JSONObject();
		JSONObject obj4 = new JSONObject();
		obj4.put("title", "book1").put("price", "$11");
		obj3.put("book", obj4);
		obj3.put("author", new JSONObject().put("name", "author-1"));

		JSONObject obj5 = new JSONObject();
		JSONObject obj6 = new JSONObject();
		obj6.put("title", "book2").put("price", "$22");
		obj5.put("book", obj6);
		obj5.put("author", new JSONObject().put("name", "author-2"));

		JSONArray obj7 = new JSONArray();
		obj7.put(obj3).put(obj5);
		obj2.put("title", "BOOK");
		obj2.put("signing", obj7);

		jsonStringer.object().key("session").value(obj2).endObject();

		System.out.println(jsonStringer.toString());
		
		PrintWriter out = new PrintWriter(
			    new BufferedWriter(
					       new FileWriter("src/testjson2.json")));
//				new PrintWriter(new FileOutputStream(
//				"src/testjson2.json"));
		out.println(jsonStringer.toString());
		out.close();
		
//		写文件最佳组合
//		PrintWriter out = new PrintWriter(
//		    new BufferedWriter(
//		       new FileWriter(filename)))
//		PrintWriter 提供print系方法
//		BufferedWriter 提供缓冲，用以加速
//		FileWriter 用于写文件 
	}

	/*
	 * JSONTokener是用来读取JSON格式的文件;
	 * 
	 * JSONObject obj = new JSONObject( new
	 * JSONTokener(java.io.Reader));可以从文件中读取一个JSONObject;
	 * 
	 * JSONArray obj = new JSONArray( new JSONTokener(java.io.Reader));
	 * 可以从文件中读取一个JSONArray;
	 */
	@Test
	public void testJSONToken() throws JSONException, FileNotFoundException {
		JSONObject obj = new JSONObject(new JSONTokener(new FileReader(
				new File("src/testjson2.json"))));
		System.out.println(obj.getJSONObject("session").toString());
		// System.out.println(obj.getJSONArray("book").getString(1)); //
		// 可以读取book2
	}

	@Test
	public void testJSONTokenComplex() throws JSONException,
			FileNotFoundException {
		JSONObject obj = new JSONObject(new JSONTokener(new FileReader(
				new File("src/testjson2.json"))));
		System.out.println(obj.getJSONObject("session").getJSONArray("signing")
				.getJSONObject(0).getJSONObject("author").getString("name"));
	}
}
