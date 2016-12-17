package com.yeahwell.demo.akka.demo02;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class LookupActor extends UntypedActor {

    @Override
    public void preStart() {
        // 创建一个名为child的子actor
        getContext().actorOf(Props.apply(ChildActor.class, null), "child");

        ActorRef child = getContext().actorFor("akka://mySystem/user/service/child");
        child.tell("from parent 1", null);
        // 使用相对路径
        child = getContext().actorFor("child");
        child.tell("from parent 2", null);

    }

    @Override
    public void onReceive(Object message) throws Exception {
        System.out.println("service 收到消息 ：" + message);
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("mySystem");
        // 创建一个名为service的顶级actor
        system.actorOf(Props.apply(LookupActor.class, null), "service");
        // 使用绝对路径获取service
        ActorRef service = system.actorFor("akka://mySystem/user/service");
        service.tell("sss", null);
        // 使用相对路径(相对于根目录的路径)
        service = system.actorFor("user/service");
        service.tell("fff", null);

        // 使用绝对路径获取child actor
        ActorRef child = system.actorFor("akka://mySystem/user/service/child");
        child.tell("sss", null);
        child = system.actorFor("user/service/child");
        child.tell("fff", null);
        system.shutdown();

    }

    static class ChildActor extends UntypedActor {
        @Override
        public void onReceive(Object message) throws Exception {
            System.out.println("child 收到消息: " + message);
        }

    }

}