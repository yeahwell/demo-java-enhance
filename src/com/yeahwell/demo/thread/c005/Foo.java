package com.yeahwell.demo.thread.c005;

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
