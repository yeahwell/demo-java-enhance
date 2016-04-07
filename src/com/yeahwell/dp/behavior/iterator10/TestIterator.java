package com.yeahwell.dp.behavior.iterator10;

public class TestIterator {

	public static void main(String[] args) {
		Collection coll = new MyCollection();
		Iterator it = (Iterator) coll.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
}
