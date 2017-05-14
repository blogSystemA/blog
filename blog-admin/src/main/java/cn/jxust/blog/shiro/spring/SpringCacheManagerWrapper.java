/**
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.jxust.blog.shiro.spring;


import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * 包装Spring cache抽象
 * <p>Version: 3.0
 */
public class SpringCacheManagerWrapper implements CacheManager {

	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		// TODO Auto-generated method stub
		return null;
	}

   
}