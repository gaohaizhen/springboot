package cn.demo.day3.aopframework;

import java.io.InputStream;
import java.util.Collection;

public class AopFrameworkTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		InputStream ips = AopFrameworkTest.class.getResourceAsStream("config.properties");
		Object bean = new cn.demo.day3.aopframework.BeanFactory(ips).getBean("xxx");
		System.out.println(bean.getClass().getName());
		((Collection)bean).clear();
	}

}
