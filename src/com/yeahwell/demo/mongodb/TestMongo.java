package com.yeahwell.demo.mongodb;

import java.net.UnknownHostException;

import org.junit.Test;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.mongodb.ServerAddress;
import com.mongodb.util.JSON;

public class TestMongo {

	public static void echo(Object str) {
		System.out.println(str);
	}

	@Test
	public void testConnection() throws UnknownHostException {
		ServerAddress serverAddress = new ServerAddress("127.0.0.1", 27017);
		MongoOptions mongoOptions = new MongoOptions();
		Mongo mongo = new Mongo(serverAddress, mongoOptions);
		//查询所有的database
		for(String name : mongo.getDatabaseNames()){
			echo("dbname: " + name);
		}
		
		DB db = mongo.getDB("test");
		 //查询所有的聚集集合
        for (String name : db.getCollectionNames()) {
           echo("collectionName: " + name);
        }
        
        DBCollection users = db.getCollection("users");
        //查询所有的数据
        DBCursor cur = users.find();
        while (cur.hasNext()) {
            echo(cur.next());
        }
        echo(cur.count());
        echo(cur.getCursorId());
        echo(JSON.serialize(cur));
	}

}
