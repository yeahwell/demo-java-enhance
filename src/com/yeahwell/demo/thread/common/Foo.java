package com.yeahwell.demo.thread.common;

public class Foo {

	private int x = 100;

	public int getX() {
		return x;
	}

	public int fix(int y) {
		x = x - y;
		return x;
	}

}
