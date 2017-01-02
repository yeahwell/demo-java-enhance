package com.yeahwell.demo.j2se.core.serialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.MessageFormat;

/**
 * <p>ClassName: TestObjSerializeAndDeserialize<p>
 * <p>Description: 测试对象的序列化和反序列<p>
 * 把对象转换为字节序列的过程称为对象的序列化。
	把字节序列恢复为对象的过程称为对象的反序列化。
	对象的序列化主要有两种用途：
	1） 把对象的字节序列永久地保存到硬盘上，通常存放在一个文件中；
	2） 在网络上传送对象的字节序列。

当两个进程在进行远程通信时，彼此可以发送各种类型的数据。无论是何种类型的数据，都会以二进制序列的形式在网络上传送。发送方需要把这个Java对象转换为字节序列，才能在网络上传送；接收方则需要把字节序列再恢复为Java对象。
 * 
 */
public class TestObjSerializeAndDeserialize {

    public static void main(String[] args) throws Exception {
        serializePerson();//序列化Person对象
        Person p = deserializePerson();//反序列Perons对象
        System.out.println(MessageFormat.format("name={0},age={1},sex={2}",
                                                 p.getName(), p.getAge(), p.getSex()));
    }
    
    /**
     * MethodName: SerializePerson 
     * Description: 序列化Person对象
     * @author xudp
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static void serializePerson() throws FileNotFoundException,
            IOException {
        Person person = new Person();
        person.setName("gacl");
        person.setAge(25);
        person.setSex("男");
        // ObjectOutputStream 对象输出流，将Person对象存储到E盘的Person.txt文件中，完成对Person对象的序列化操作
        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(
                new File("/Users/yeahwell/Person.txt")));
        oo.writeObject(person);
        System.out.println("Person对象序列化成功！");
        oo.close();
    }

    /**
     * MethodName: DeserializePerson 
     * Description: 反序列Perons对象
     * @author xudp
     * @return
     * @throws Exception
     * @throws IOException
     */
    private static Person deserializePerson() throws Exception, IOException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                new File("/Users/yeahwell/Person.txt")));
        Person person = (Person) ois.readObject();
        System.out.println("Person对象反序列化成功！");
        return person;
    }

}