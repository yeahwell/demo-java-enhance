package com.yeahwell.demo.callback;

import java.text.SimpleDateFormat;
import java.util.Date;

//相当于class A  
public class At$911 implements BoomWTC {// 相当于【背景1】

	private boolean decide;

	private TerroristAttack ta;// 相当于【背景2】

	public At$911() {
		Date now = new Date();
		SimpleDateFormat myFmt1 = new SimpleDateFormat("yy/MM/dd HH:mm");
		this.decide = myFmt1.format(now).equals("01/09/11 09:44");
		this.ta = new TerroristAttack();
	}

	// 获得拉登的决定
	public boolean benLaDengDecide() {
		return decide;
	}

	// 执行轰炸世贸
	public void boom(){  
		ta.attack(new At$911());//class A调用class B的方法传入自己的对象，相当于【you call me】  
	}
	
}
