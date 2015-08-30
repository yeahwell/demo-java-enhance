package com.yeahwell.dp.create.builder.oneactor;

public class ManBuilder{
    Man man;
    public ManBuilder() {
         man = new Man();
    }
    public void buildBody() {
         man.setBody("建造男人的身体");
    }
    public void buildFoot() {
         man.setFoot("建造男人的脚");
    }
    public void buildHead() {
         man.setHead("建造男人的头");
    }
    public Man builderMan() {
         buildHead();
         buildBody();
         buildFoot();
         return man;
    }
}
