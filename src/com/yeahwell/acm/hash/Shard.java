package com.yeahwell.acm.hash;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * S类封装了机器节点的信息 ，如name、password、ip、port等  
 * @author yeahwell
 *
 */
public class Shard {
	
	private TreeMap<Long, Node> virtual2realNodeMap; //虚拟节点到真实节点的映射
	private TreeMap<Long, Node> key2realNodeMap; //key到真实节点的映射
	private List<Node> realNodeList = new ArrayList<Node>(); // 真实机器节点  
	private static final int NODE_NUM = 100; //每个机器节点关联的虚拟节点个数
	private boolean flag = false;

	public Shard(List<Node> realNodeList){
		super();
		this.realNodeList = realNodeList;
		init();
	}
	

	
	public void printKeyTree(){
		for(Iterator<Long> it = key2realNodeMap.keySet().iterator(); it.hasNext(); ){
			Long lo = it.next();
			System.out.println("hash(" + lo + ")连接到主机->" + key2realNodeMap.get(lo));
		}
	}
	
	//初始化一致性hash环
	private void init(){
		virtual2realNodeMap = new TreeMap<Long, Node>();
		key2realNodeMap = new TreeMap<Long, Node>();
		for(int i = 0; i != realNodeList.size(); i++){
			final Node shardInfo = realNodeList.get(i);
			for(int n = 0; n < NODE_NUM; n++){
				//一个真实机器节点关联NODE_NUM个虚拟节点
				virtual2realNodeMap.put(hash("SHARD-" + shardInfo.name + "-NODE-" + n), shardInfo);
			}
		}
	}
	
	//增加一个主机
	public void addShard(Node s){
		System.out.println("增加主机" + s + "的变化");
		for(int n = 0; n < NODE_NUM; n++){
			addShard(hash("SHARD-" + s.name + "-NODE-" + n), s);
		}
	}
	
	//添加一个虚拟节点进环形结构,lg为虚拟节点的hash值
	private void addShard(Long lg, Node s){
		SortedMap<Long, Node> tail = virtual2realNodeMap.tailMap(lg);
		SortedMap<Long, Node> head = virtual2realNodeMap.headMap(lg);
		Long begin = 0L;
		Long end = 0L;
		SortedMap<Long, Node> between;
		if(0 == head.size()){
			between = key2realNodeMap.tailMap(virtual2realNodeMap.lastKey());
			flag = true;
		}else{
			begin = head.lastKey();
			between = key2realNodeMap.subMap(begin, lg);
			flag = false;
		}
		virtual2realNodeMap.put(lg, s);
		for(Iterator<Long> it = between.keySet().iterator(); it.hasNext(); ){
			Long lo = it.next();
			if(flag){
				key2realNodeMap.put(lo, virtual2realNodeMap.get(lg));
				System.out.println("hash(" + lo + ")改变到->" + tail.get(tail.firstKey()));
			}else{
				key2realNodeMap.put(lo, virtual2realNodeMap.get(lg));
				System.out.println("hash(" + lo + ")改变到->" + tail.get(tail.firstKey()));
			}
		}
	}
	
	//删除真实节点
	public void deleteShard(Node s){
		if(null == s){
			return;
		}
		System.out.println("删除主机" + s + "的变化: ");
		for(int i = 0; i < NODE_NUM; i++){
			//定位s节点的第i的虚拟节点的位置
			SortedMap<Long, Node> tail = virtual2realNodeMap.tailMap(hash("SHARD-" + s.name + "-NODE-" + i));  
            SortedMap<Long,Node>  head = virtual2realNodeMap.headMap(hash("SHARD-" + s.name + "-NODE-" + i));  
            Long begin = 0L;  
            Long end = 0L;  
              
            SortedMap<Long, Node> between;  
            if(head.size()==0){  
                between = key2realNodeMap.tailMap(virtual2realNodeMap.lastKey());  
                end = tail.firstKey();  
                tail.remove(tail.firstKey());  
                virtual2realNodeMap.remove(tail.firstKey());//从nodes中删除s节点的第i个虚拟节点  
                flag = true;  
            }else{  
                begin = head.lastKey();  
                end = tail.firstKey();  
                tail.remove(tail.firstKey());  
                between = key2realNodeMap.subMap(begin, end);//在s节点的第i个虚拟节点的所有key的集合  
                flag = false;  
            }  
            for(Iterator<Long> it = between.keySet().iterator();it.hasNext();){  
                Long lo  = it.next();  
                if(flag){  
                    key2realNodeMap.put(lo, tail.get(tail.firstKey()));  
                    System.out.println("hash("+lo+")改变到->"+tail.get(tail.firstKey()));  
                }else{  
                    key2realNodeMap.put(lo, tail.get(tail.firstKey()));  
                    System.out.println("hash("+lo+")改变到->"+tail.get(tail.firstKey()));  
                }  
            }  
		}
	}
	
	//映射key到真实节点  
    public void keyToNode(String key){  
        SortedMap<Long, Node> tail = virtual2realNodeMap.tailMap(hash(key)); // 沿环的顺时针找到一个虚拟节点  
        if (tail.size() == 0) {  
            return;  
        }  
        key2realNodeMap.put(hash(key), tail.get(tail.firstKey()));  
        System.out.println(key+"（hash："+hash(key)+"）连接到主机->"+tail.get(tail.firstKey()));  
    }  
    
    /** 
    *  MurMurHash算法，是非加密HASH算法，性能很高， 
    *  比传统的CRC32,MD5，SHA-1（这两个算法都是加密HASH算法，复杂度本身就很高，带来的性能上的损害也不可避免） 
    *  等HASH算法要快很多，而且据说这个算法的碰撞率很低. 
    *  http://murmurhash.googlepages.com/ 
    */  
   public static Long hash(String key) {  
       
//	   long startTime = System.nanoTime();
	   
       ByteBuffer buf = ByteBuffer.wrap(key.getBytes());  
       int seed = 0x1234ABCD;  
         
       ByteOrder byteOrder = buf.order();  
       buf.order(ByteOrder.LITTLE_ENDIAN);  
 
       long m = 0xc6a4a7935bd1e995L;  
       int r = 47;  
 
       long h = seed ^ (buf.remaining() * m);  
 
       long k;  
       while (buf.remaining() >= 8) {  
           k = buf.getLong();  
 
           k *= m;  
           k ^= k >>> r;  
           k *= m;  
 
           h ^= k;  
           h *= m;  
       }  
 
       if (buf.remaining() > 0) {  
           ByteBuffer finish = ByteBuffer.allocate(8).order(  
                   ByteOrder.LITTLE_ENDIAN);  
           // for big-endian version, do this first:  
           // finish.position(8-buf.remaining());  
           finish.put(buf).rewind();  
           h ^= finish.getLong();  
           h *= m;  
       }  
 
       h ^= h >>> r;  
       h *= m;  
       h ^= h >>> r;  
 
       buf.order(byteOrder);  
       
       
//       long endTime = System.nanoTime();
//       System.out.println("hash(" + key + ")耗时" + (endTime - startTime) + "毫秒");
       return h;  
   }  

	


}
