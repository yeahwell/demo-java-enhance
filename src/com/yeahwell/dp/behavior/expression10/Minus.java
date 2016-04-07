package com.yeahwell.dp.behavior.expression10;

public class Minus implements Expression {  
	 
    @Override 
    public int interpret(Context context) {  
        return context.getNum1()-context.getNum2();  
    }  
}
