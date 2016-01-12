package com.yeahwell.demo.oom;

import java.util.ArrayList;
import java.util.List;

public class HeapOOM{
	
    public static void main(String[] args){
        List<Person> list = new ArrayList<Person>();
        int i = 0;
        while(true){
        	i++;
            list.add(new Person(i + ""));
        }
    }
    
}

class Person{
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Person(String name) {
		super();
		this.name = name;
	}
	
}
