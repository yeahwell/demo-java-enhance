package com.yeahwell.dp.create.builder.actor;

//角色Director
public class PersonDirector {

	public Person constructPerson(PersonBuilder pb) {  
        pb.buildHead();  
        pb.buildBody();  
        pb.buildFoot();  
        return pb.buildPerson();  
   }  
}
