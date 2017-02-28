package com.yeahwell.demo.j2se.core.collect.set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * 元素加入List的时候，不执行额外的操作，并且可以重复。
 * 而加入Set之前需要先执行hashCode方法，如果返回的值在集合中已存在，则要继续执行equals方法，
 * 如果equals方法返回的结果也为真，则证明该元素已经存在，会将新的元素覆盖老的元素，如果返回hashCode值不同，则直接加入集合。
 * 这里记住一点，对于集合中元素，hashCode值不同的元素一定不相等，但是不相等的元素，hashCode值可能相同。
 * @author yeahwell
 *
 */
public class SetTest {

    public static void main(String[] args) {
        Person p1 = new Person("lxp",10);
        Person p2 = new Person("lxp",10);
        Person p3 = new Person("lxp",20);

        ArrayList<Person> list = new ArrayList<Person>();
        list.add(p1);
        System.out.println("---------");
        list.add(p2);
        System.out.println("---------");
        list.add(p3);
        System.out.println("List size=" + list.size());

        System.out.println("----分割线-----");

        Set<Person> set = new HashSet<Person>();
        set.add(p1);
        System.out.println("---------");
        set.add(p2);
        System.out.println("---------");
        set.add(p3);
        System.out.println("Set size="+set.size());
    }


    static class Person{
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            System.out.println("Call equals();name="+name);
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Person person = (Person) o;

            return name.equals(person.name);

        }

        @Override
        public int hashCode() {
            System.out.println("Call hashCode(),age="+age);
            return age;
        }
    }
}