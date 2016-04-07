package com.yeahwell.dp.behavior.command10;

public class MyCommand implements Command{

	private Receiver receiver;
	
	public MyCommand(Receiver receiver) {
		super();
		this.receiver = receiver;
	}

	@Override
	public void exe() {
		receiver.action();
	}

}
