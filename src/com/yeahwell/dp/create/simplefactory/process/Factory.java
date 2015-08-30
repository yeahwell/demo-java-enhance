package com.yeahwell.dp.create.simplefactory.process;

public class Factory {

	public static BMW createBMW(int type) {
		switch (type) {
		case 320:
			return new BMW320();

		case 523:
			return new BMW523();

		default:
			break;
		}
		return null;
	}
	
}
