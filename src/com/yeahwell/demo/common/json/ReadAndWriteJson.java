package com.yeahwell.demo.common.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.JSONException;
import org.json.JSONObject;

public class ReadAndWriteJson {

	/**
	 * @param args
	 * @throws JSONException
	 * @throws IOException
	 */
	public static void main(String[] args) throws JSONException, IOException {
		// TODO Auto-generated method stub

		// String s = ReadFile("./src/test.json");
		// System.out.println(s);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("1", "一");
		jsonObject.put("2", "二");
		jsonObject.put("3", "三");
		jsonObject.put("4", "四");
		jsonObject.put("5", "五");
		jsonObject.put("6", "六");
		jsonObject.put("7", "⑦");
		//System.out.println(jsonObject);

		writeFile("./src/test.json", jsonObject.toString());
	}

	public static void writeFile(String filePath, String sets)
			throws IOException {
		FileWriter fileWriter = new FileWriter(filePath);
//		PrintWriter out = new PrintWriter(fileWriter);
//		out.write(sets);
//		out.println();
		fileWriter.write(sets);
		fileWriter.close();
//		out.close();
	}

	public static String ReadFile(String path) {
		File file = new File(path);
		BufferedReader reader = null;
		String laststr = "";
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				laststr = laststr + tempString;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return laststr;
	}
}
