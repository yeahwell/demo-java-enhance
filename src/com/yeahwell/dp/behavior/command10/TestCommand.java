package com.yeahwell.dp.behavior.command10;

public class TestCommand {

	public static void main(String[] args) {
		Receiver receiver = new Receiver();
		Command cmd = new MyCommand(receiver);
		Invoker invoker = new Invoker(cmd);
		invoker.action();
		
	}
	
}
