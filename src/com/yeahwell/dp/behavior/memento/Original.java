package com.yeahwell.dp.behavior.memento;

public class Original {

	private String value;
	
	public String getValue(){
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public Original(String value){
		this.value = value;
	}
	
	public Memento createMemento(){
		return new Memento(value);
	}
	
	public void restorMemento(Memento memento){
		this.value = memento.getValue();
	}
	
}
