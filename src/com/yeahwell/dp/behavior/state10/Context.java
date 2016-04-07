package com.yeahwell.dp.behavior.state10;

public class Context {

	private State state;
	
	public Context(State state){
		this.state = state;
	}
	
	public State getState(){
		return state;
	}
	
	public void method(){
		System.out.println("状态为: "+ state.getValue());
		if(state.getValue().equals("state1")){
			state.method1();
		}else if(state.getValue().equals("state2")){
			state.method2();
		}
	}
	
}
