package com.yeahwell.demo.j2se.core.serialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 
 * @author yeahwell
 　　serialVersionUID的取值是Java运行时环境根据类的内部细节自动生成的。如果对类的源代码作了修改，再重新编译，新生成的类文件的serialVersionUID的取值有可能也会发生变化。
　　类的serialVersionUID的默认值完全依赖于Java编译器的实现，对于同一个类，用不同的Java编译器编译，有可能会导致不同的 serialVersionUID，也有可能相同。为了提高serialVersionUID的独立性和确定性，强烈建议在一个可序列化类中显示的定义serialVersionUID，为它赋予明确的值。

　　显式地定义serialVersionUID有两种用途：
　　　　1、 在某些场合，希望类的不同版本对序列化兼容，因此需要确保类的不同版本具有相同的serialVersionUID；
　　　　2、 在某些场合，不希望类的不同版本对序列化兼容，因此需要确保类的不同版本具有不同的serialVersionUID。
 */
public class TestSerialversionUID {

    public static void main(String[] args) throws Exception {
        SerializeCustomer();// 序列化Customer对象
        /**
         * 在TestSerialversionUID例子中，没有指定Customer类的serialVersionUID的，那么java编译器会自动给这个class进行一个摘要算法，类似于指纹算法，只要这个文件 多一个空格，得到的UID就会截然不同的，
         * 可以保证在这么多类中，这个编号是唯一的。
         * 所以，添加了一个字段后，由于没有显指定 serialVersionUID，编译器又为我们生成了一个UID，
         * 当然和前面保存在文件中的那个不会一样了，于是就出现了2个序列化版本号不一致的错误。
         * 因此，只要我们自己指定了serialVersionUID，就可以在序列化后，去添加一个字段，或者方法，而不会影响到后期的还原，还原后的对象照样可以使用，而且还多了方法或者属性可以用。
         */
        Customer customer = DeserializeCustomer();// 反序列Customer对象
        System.out.println(customer);
    }

    /**
     * MethodName: SerializeCustomer 
     * Description: 序列化Customer对象
     * @author xudp
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static void SerializeCustomer() throws FileNotFoundException,
            IOException {
        Customer customer = new Customer("gacl",25);
        // ObjectOutputStream 对象输出流
        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(
                new File("/Users/yeahwell/Customer.txt")));
        oo.writeObject(customer);
        System.out.println("Customer对象序列化成功！");
        oo.close();
    }

    /**
     * MethodName: DeserializeCustomer 
     * Description: 反序列Customer对象
     * @author xudp
     * @return
     * @throws Exception
     * @throws IOException
     */
    private static Customer DeserializeCustomer() throws Exception, IOException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                new File("/Users/yeahwell/Customer.txt")));
        Customer customer = (Customer) ois.readObject();
        System.out.println("Customer对象反序列化成功！");
        return customer;
    }
}

/**
 * <p>ClassName: Customer<p>
 * <p>Description: Customer实现了Serializable接口，可以被序列化<p>
 * @author xudp
 * @version 1.0 V
 * @createTime 2014-6-9 下午04:20:17
 */
class Customer implements Serializable {
    //Customer类中没有定义serialVersionUID
    private String name;
    private int age;
    
    //新添加的sex属性，然后执行反序列操作，会报异常
   private String sex;

    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /*
     * @MethodName toString
     * @Description 重写Object类的toString()方法
     * @author xudp
     * @return string
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "name=" + name + ", age=" + age;
    }
}