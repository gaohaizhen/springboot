package com.demo.starter.redis;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * <b><code>JedisClusterManager</code></b>
 * <p>
 * 请保证该类在SPRING容器中为单例.
 * </p>
 * <b>Creation Time:</b> 2016年6月19日 下午10:38:26
 * @author <a href=zhangsiwei@100bei.com>zhangsiwei</a>
 * @since baibei 1.0
 */
public class JedisClusterManager {

	private static final int DEFAULT_TIMEOUT = 1000;
	private static final int DEFAULT_MAX_REDIRECTIONS = 20;
	
	private static final int MAX_TOTAL = 100;
	private static final int MAX_IDLE = 40;
	private static final int MIN_IDLE = 20;
	private static final boolean TEST_ON_BORROW = true;
	private static final boolean TEST_WHILE_IDLE = true;
	private static final int MAX_WAITE = 30000;

	private String servers;
	private int timeout = DEFAULT_TIMEOUT;
	private int maxRedirections = DEFAULT_MAX_REDIRECTIONS;
	private JedisCluster jedisCluster;

	/**
	 * 
	 * 该方法只应该被执行一次.
	 *
	 * @since baibei 1.0
	 */
	public void init() {
		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();

		for (String server : servers.split("[,]")) {
			String[] sa = server.split("[:]");
			if (sa.length == 2) {
				String host = sa[0];
				int port = Integer.parseInt(sa[1]);
				jedisClusterNodes.add(new HostAndPort(host, port));
			}
		}

		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(MAX_TOTAL);
		jedisPoolConfig.setMaxIdle(MAX_IDLE);
		jedisPoolConfig.setMinIdle(MIN_IDLE);
		jedisPoolConfig.setMaxWaitMillis(MAX_WAITE);
		jedisPoolConfig.setTestOnBorrow(TEST_ON_BORROW);
		jedisPoolConfig.setTestWhileIdle(TEST_WHILE_IDLE);
		
		//Jedis Cluster will attempt to discover cluster nodes automatically
		jedisCluster = new JedisCluster(jedisClusterNodes, timeout, maxRedirections, jedisPoolConfig);
	}

	public JedisCluster getJedisCluster() {
		if (null == jedisCluster) {
			init();
		}
		return jedisCluster;
	}

	public String getServers() {
		return servers;
	}

	public void setServers(String servers) {
		this.servers = servers;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getMaxRedirections() {
		return maxRedirections;
	}

	public void setMaxRedirections(int maxRedirections) {
		this.maxRedirections = maxRedirections;
	}
}
