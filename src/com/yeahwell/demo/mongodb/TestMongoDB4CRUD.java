package com.yeahwell.demo.mongodb;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class TestMongoDB4CRUD {

	private Mongo mg = null;
	private DB db;
	private DBCollection users;

	public static void echo(Object str) {
		System.out.println(str);
	}

	@Before
	public void init() {
		try {
			mg = new Mongo();
			// mg = new Mongo("localhost", 27017);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		// 获取temp DB；如果默认没有创建，mongodb会自动创建
		db = mg.getDB("temp");
		// 获取users DBCollection；如果默认没有创建，mongodb会自动创建
		users = db.getCollection("users");
	}

	@After
	public void destory() {
		if (mg != null) {
			mg.close();
		}
		mg = null;
		db = null;
		users = null;
		System.gc();
	}

	private void queryAll() {
		echo("查询users的所有数据");
		DBCursor cursor = users.find();
		while (cursor.hasNext()) {
			echo(cursor.next());
		}
	}

	@Test
	public void testAdd() {
		// 先查询所有数据
		queryAll();
		echo("count: " + users.count());

		DBObject user = new BasicDBObject();
		user.put("name", "yeahwell");
		user.put("age", 24);
//		users.save(user);
//		echo(users.save(user).getN());

		// 扩展字段，随意添加字段，不影响现有数据
		user.put("sex", "男");
		echo(users.save(user).getN());

		// 添加多条数据，传递Array对象
		echo(users.insert(user, new BasicDBObject("name", "tom")).getN());

		// 添加List集合
		List<DBObject> list = new ArrayList<DBObject>();
		list.add(user);
		DBObject user2 = new BasicDBObject("name", "lucy");
		user.put("age", 22);
		list.add(user2);
		// 添加List集合
		echo(users.insert(list).getN());

		// 查询下数据，看看是否添加成功
		echo("count: " + users.count());
		queryAll();

	}

}
