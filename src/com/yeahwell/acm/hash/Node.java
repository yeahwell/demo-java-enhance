package com.yeahwell.acm.hash;

public class Node {
	
	public String name;
	public String ip;

	public Node(String name, String ip) {
		this.name = name;
		this.ip = ip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Node [name=").append(name).append(", ip=").append(ip)
				.append("]");
		return builder.toString();
	}
}
