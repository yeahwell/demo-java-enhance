package com.yeahwell.demo.j2se.core.serialize;

//import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.IOUtils;

import java.io.*;
/**
 * Created by hollis on 16/2/17.
 * SerializableDemo1 结合SerializableDemo2说明 一个类要想被序列化必须实现Serializable接口
 */
public class SerializableDemo1 {

    public static void main(String[] args) {
        //Initializes The Object
        User1 user = new User1();
        user.setName("hollis");
        user.setAge(23);
        System.out.println(user);

        //Write Obj to File
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("tempFile"));
            oos.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            IOUtils.closeQuietly(oos);
        	try {
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        //Read Obj from File
        File file = new File("tempFile");
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            User1 newUser = (User1) ois.readObject();
            System.out.println(newUser);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
//            IOUtils.closeQuietly(ois);
        	try {
				ois.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
          //  file.delete();
//                FileUtils.forceDelete(file);
        }

    }
}

//OutPut:
//User{name='hollis', age=23}
//User{name='hollis', age=23}