package com.yeahwell.demo.common.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;

/**
 * java中多种方式读文件 一、多种方式读文件内容。 1、按字节读取文件内容 2、按字符读取文件内容 3、按行读取文件内容 4、随机读取文件内容
 */
public class TestReadFromFile {

	/**
	 * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
	 * 
	 * @param fileName
	 *            文件的名
	 */
	public void readFileByBytes(String fileName) {
		File file = new File(fileName);
		InputStream inputStream = null;

		// 一次读一个字节
		System.out.println("以字节为单位读取文件内容，一次读一个字节：");
		try {
			inputStream = new FileInputStream(file);
			int tempbyte = 0;
			while ((tempbyte = inputStream.read()) != -1) {
				System.out.write(tempbyte);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			}
		}

	}

	public void readFileByMultBytes(String fileName) {
		InputStream inputStream = null;
		// 一次读多个字节
		System.out.println("以字节为单位读取文件内容，一次读多个字节：");
		try {
			byte[] tempbytes = new byte[100];
			int byteread = 0;
			inputStream = new FileInputStream(fileName);
			// 显示输入流中还剩的字节数
			System.out.println("当前字节输入流中的字节数为:" + inputStream.available());
			// 读入多个字节到字节数组中，byteread为一次读入的字节数
			while ((byteread = inputStream.read(tempbytes)) != -1) {
				System.out.write(tempbytes, 0, byteread);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * 以字符为单位读取文件，常用于读文本，数字等类型的文件
	 * 
	 * @param fileName
	 *            文件名
	 */
	public void readFileByChars(String fileName) {
		File file = new File(fileName);
		Reader reader = null;
		try {
			System.out.println("以字符为单位读取文件内容，一次读一个字节：");
			// 一次读一个字符
			reader = new InputStreamReader(new FileInputStream(file));
			int tempchar = 0;
			while ((tempchar = reader.read()) != -1) {
				// 对于windows下，rn这两个字符在一起时，表示一个换行。
				// 但如果这两个字符分开显示时，会换两次行。
				// 因此，屏蔽掉r，或者屏蔽n。否则，将会多出很多空行。
				// if (((char) tempchar) != 'r') {
				System.out.print((char) tempchar);
				// }
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readFileByMultChars(String fileName) {
		Reader reader = null;
		try {
			System.out.println("以字符为单位读取文件内容，一次读多个字节：");
			// 一次读多个字符
			char[] tempchars = new char[30];
			int charread = 0;
			reader = new InputStreamReader(new FileInputStream(fileName));
			// 读入多个字符到字符数组中，charread为一次读取字符数
			while ((charread = reader.read(tempchars)) != -1) {
				// 同样屏蔽掉r不显示
				if ((charread == tempchars.length)
						&& (tempchars[tempchars.length - 1] != 'r')) {
					System.out.print(tempchars);
				} else {
					for (int i = 0; i < charread; i++) {
						if (tempchars[i] == 'r') {
							continue;
						} else {
							System.out.print(tempchars[i]);
						}
					}
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 * 
	 * @param fileName
	 *            文件名
	 */
	public void readFileByLines(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				System.out.println("line " + line + ": " + tempString);
				line++;
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
	}

	/**
	 * 随机读取文件内容
	 * 
	 * @param fileName
	 *            文件名
	 */
	public void readFileByRandomAccess(String fileName) {
		RandomAccessFile randomFile = null;
		System.out.println("随机读取一段文件内容：");
		try {
			// 打开一个随机访问文件流，按只读方式
			randomFile = new RandomAccessFile(fileName, "r");
			// 文件长度，字节数
			long fileLength = randomFile.length();
			System.out.println("字节数为" + fileLength);
			// 读文件的起始位置
			int beginIndex = (fileLength > 4) ? 4 : 0;
			// 将读文件的开始位置移到beginIndex位置。
			randomFile.seek(beginIndex);
			byte[] bytes = new byte[10];
			int byteread = 0;
			// 一次读10个字节，如果文件内容不足10个字节，则读剩下的字节。
			// 将一次读取的字节数赋给byteread
			while ((byteread = randomFile.read(bytes)) != -1) {
				System.out.write(bytes, 0, byteread);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (randomFile != null) {
				try {
					randomFile.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	public static void main(String[] args) {
		String fileName = "c:/newTemp.txt";
		TestReadFromFile readFromFile = new TestReadFromFile();
		
//		long start = 0, end = 0;
//		start = System.currentTimeMillis();
//		readFromFile.readFileByBytes(fileName);
//		end = System.currentTimeMillis();
//		System.out.println("用时---" + (end - start) + "毫秒");
//
//		start = System.currentTimeMillis();
		readFromFile.readFileByMultBytes(fileName);
//		end = System.currentTimeMillis();
//		System.out.println("用时---" + (end - start) + "毫秒");
		 

		// readFromFile.readFileByChars(fileName);
		// readFromFile.readFileByMultChars(fileName);
		// readFromFile.readFileByLines(fileName);
//		readFromFile.readFileByRandomAccess(fileName);

	}
}
