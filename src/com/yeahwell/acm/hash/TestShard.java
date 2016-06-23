package com.yeahwell.acm.hash;

import java.util.ArrayList;
import java.util.List;

public class TestShard {

	public static void main(String[] args) {
		System.out.println(Shard.hash("w222o1d"));
		System.out.println(Long.MIN_VALUE);
		System.out.println(Long.MAX_VALUE);
		Node s1 = new Node("s1", "192.168.1.1");
		Node s2 = new Node("s2", "192.168.1.2");
		Node s3 = new Node("s3", "192.168.1.3");
		Node s4 = new Node("s4", "192.168.1.4");
		Node s5 = new Node("s5", "192.168.1.5");
		List<Node> realNodeList = new ArrayList<Node>();
		realNodeList.add(s1);
		realNodeList.add(s2);
		realNodeList.add(s3);
		realNodeList.add(s4);
		Shard sh = new Shard(realNodeList);
		System.out.println("添加客户端，一开始有4个主机，分别为s1,s2,s3,s4,每个主机有100个虚拟主机：");
		
		sh.keyToNode("101客户端");
		sh.keyToNode("102客户端");
		sh.keyToNode("103客户端");
		sh.keyToNode("104客户端");
		sh.keyToNode("105客户端");
		sh.keyToNode("106客户端");
		sh.keyToNode("107客户端");
		sh.keyToNode("108客户端");
		sh.keyToNode("109客户端");

		sh.deleteShard(s2);
		sh.addShard(s5);

		System.out.println("最后的客户端到主机的映射为：");
		sh.printKeyTree();
	}

}
