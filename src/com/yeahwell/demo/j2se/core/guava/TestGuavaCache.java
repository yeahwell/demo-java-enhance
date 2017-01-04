package com.yeahwell.demo.j2se.core.guava;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class TestGuavaCache {

	@Test
	public void testLoadCache() throws ExecutionException {
		LoadingCache<String, String> cacheBuilder = CacheBuilder.newBuilder()
				.build(new CacheLoader<String, String>() {
					@Override
					public String load(String key) throws Exception {
						String strProValue = "hello " + key;
						return strProValue;
					}
				});
		System.out.println(cacheBuilder.apply("jerry"));
		System.out.println(cacheBuilder.get("jerry"));
		System.out.println("peida value:" + cacheBuilder.get("peida"));
		System.out.println("peida value:" + cacheBuilder.apply("peida"));
		System.out.println("lisa value:" + cacheBuilder.apply("lisa"));
		cacheBuilder.put("harry", "ssdded");
		System.out.println("harry value:" + cacheBuilder.get("harry"));
	}

	@Test
	public void testCallableCache() throws Exception {
		/**
		 * 
		 	回收的参数：
		　　1. 大小的设置：CacheBuilder.maximumSize(long)  CacheBuilder.weigher(Weigher)  CacheBuilder.maxumumWeigher(long)
		　　2. 时间：expireAfterAccess(long, TimeUnit) expireAfterWrite(long, TimeUnit)
		　　3. 引用：CacheBuilder.weakKeys() CacheBuilder.weakValues()  CacheBuilder.softValues()
		　　4. 明确的删除：invalidate(key)  invalidateAll(keys)  invalidateAll()
		　　5. 删除监听器：CacheBuilder.removalListener(RemovalListener)
		 */
		Cache<String, String> cache = CacheBuilder.newBuilder()
				.maximumSize(1000).build();
		String resultVal = cache.get("jerry", new Callable<String>() {
			public String call() {
				String strProValue = "hello " + "jerry" + "!";
				return strProValue;
			}
		});
		System.out.println("jerry value : " + resultVal);
		resultVal = cache.get("peida", new Callable<String>() {
			public String call() {
				String strProValue = "hello " + "peida" + "!";
				return strProValue;
			}
		});
		System.out.println("peida value : " + resultVal);
	}

}
