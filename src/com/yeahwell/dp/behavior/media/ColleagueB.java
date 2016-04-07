package com.yeahwell.dp.behavior.media;

public class ColleagueB extends AbstractColleague {

	public ColleagueB(AbstractMediator mediator) {
		super(mediator);
	}
	
	//每个具体同事必然有自己分内的事，没必要与外界相关联  
	public void self() {
		System.out.println("同事B --> 做好自己分内的事情 ...");
	}
	
	//每个具体同事总有需要与外界交互的操作，通过中介者来处理这些逻辑并安排工作 
	public void out() {
		System.out.println("同事B --> 请求同事A做好分内工作 ...");
		super.mediator.execute("ColleagueB", "self");
	}

}