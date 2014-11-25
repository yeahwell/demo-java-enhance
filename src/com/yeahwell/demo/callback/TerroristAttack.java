package com.yeahwell.demo.callback;

//相当于class B  
public class TerroristAttack {
	
	public TerroristAttack() {
	}

	public void attack(BoomWTC bmw) {// ——这相当于【背景3】
		if (bmw.benLaDengDecide()) {// class B在方法中回调class A的方法，相当于【i call you
									// back】
			// let's go.........
		}
	}
}