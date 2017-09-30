package cn.demo.day1;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

public class IntroSpectorTest {

	public static void main(String[] args) throws Exception {
		ReflectPoint pt1 = new ReflectPoint(3,5);
		
		String propertyName = "x";
		//"x"-->"X"-->"getX"-->MethodGetX-->
		Object retVal = getProperty(pt1, propertyName);
		System.out.println(retVal);
		
		Object value = 7;
		
		setProperties(pt1, propertyName, value);

		System.out.println(BeanUtils.getProperty(pt1, "x").getClass().getName());
		BeanUtils.setProperty(pt1, "x", "9");
		System.out.println(pt1.getX());
		
		BeanUtils.setProperty(pt1, "birthday.time", "111");
		System.out.println(BeanUtils.getProperty(pt1, "birthday.time"));
		
		PropertyUtils.setProperty(pt1, "x", 9);
		System.out.println(PropertyUtils.getProperty(pt1, "x").getClass().getName());
		
	}

	private static void setProperties(Object pt1, String propertyName,
			Object value) throws IntrospectionException,
			IllegalAccessException, InvocationTargetException {
		PropertyDescriptor pd2 = new PropertyDescriptor(propertyName,pt1.getClass());
		Method methodSetX = pd2.getWriteMethod();
		methodSetX.invoke(pt1,value);
	}

	private static Object getProperty(Object pt1, String propertyName)
            throws Exception {
		BeanInfo beanInfo =  Introspector.getBeanInfo(pt1.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		Object retVal = null;
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            if (propertyDescriptor.getName().equals(propertyName))
			{
                Method methodGetX = propertyDescriptor.getReadMethod();
				retVal = methodGetX.invoke(pt1);
				break;
			}
		}
		return retVal;
	}

}
