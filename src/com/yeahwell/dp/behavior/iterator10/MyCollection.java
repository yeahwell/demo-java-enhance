package com.yeahwell.dp.behavior.iterator10;

public class MyCollection implements Collection{

	public String string[] = {"A", "B", "C", "D", "E"};
	
	@Override
	public Iterator iterator() {
		return new MyIterator(this);
	}

	@Override
	public Object get(int i) {
		return string[i];
	}

	@Override
	public int size() {
		return string.length;
	}

}
