package com.penban.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

/**
 * 
 * @类名称：ObjectUtil
 * @类描述：打印对象属性
 * @作者：ricky
 * @日期：2012-7-01
 */
public class ObjectUtil {

	private static String FormatString(String targetStr, int strLength) {
		int curLength = targetStr.getBytes().length;
		if (targetStr != null && curLength > strLength) {
			targetStr = targetStr.substring(0, strLength);
		}
		String newString = "";
		int cutLength = strLength - targetStr.getBytes().length;
		for (int i = 0; i < cutLength; i++)
			newString += " ";
		return targetStr + newString;
	}

	/**
	 * 
	 * @param entity
	 * @return 获取对象所有属性名称及值
	 * @throws Exception
	 */
	private static String getPropertyString(int i, Class<?> c, Object entityName) throws Exception {
		// Class c = entityName.getClass();
		String className = c.getName();
		Field field[] = c.getDeclaredFields();
		StringBuffer sb = new StringBuffer();
		String space = "";
		space = FormatString(space, i);
		sb.append("\n" + space + className + " 〖Begin〗 \n");
		for (Field f : field) {
			f.setAccessible(true);
			if (isBaseClass(f.getType())) {
				if (!f.getName().equals("serialVersionUID")) {
					sb.append(space + f.getName());
					sb.append(" : ");
					sb.append(f.get(entityName));
					sb.append("\n");
				}
			} else {
				String name = f.getName();
				String firstLetter = name.substring(0, 1).toUpperCase();
				String getMethodName = "get" + firstLetter + name.substring(1);
				Method getMethod = c.getMethod(getMethodName, new Class[] {});
				Object value = getMethod.invoke(entityName, new Object[] {});
				if (value != null) {
					sb.append(getPropertyString(i + 6, value.getClass(), value));
					sb.append("\n");
				} else {
					sb.append(space + f.getName());
					sb.append(" : ");
					sb.append(f.get(entityName));
					sb.append("\n");
				}
			}
		}
		sb.append(space + className + " 〖End〗");
		if (c.getSuperclass() == null || c.getSuperclass() == java.io.Serializable.class || c.getSuperclass() == java.lang.Object.class)
			return sb.toString();
		sb.append(getPropertyString(i, c.getSuperclass(), entityName));
		return sb.toString();
	}

	private static boolean isBaseClass(Class<?> c) {
		if (c.isPrimitive() || c.getPackage().getName().equals("java.lang") || c.getPackage().getName().equals("java.util"))
			return true;
		else
			return false;
	}

	public static String returnCollectionPropertyString(Collection<?> collection) {
		StringBuffer s = new StringBuffer();
		for (Iterator<?> iterator = collection.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			s.append("\n------------------------------------------------------------------------------------------------------------------------");
			s.append("             ").append(returnPropertyString(object));
		}
		return s.toString();
	}

	public static String returnPropertyString(Object object) {
		try {
			if (object == null) {
				return "Object is NULL.";
			}
			String beanToString = ObjectUtil.getPropertyString(42, object.getClass(), object);
			return beanToString;
		} catch (Exception e) {
			return "Read object properties error.";
		}
	}

	public static String returnToLine(Object object) {
		try {
			if (object == null) {
				return "Object is NULL.";
			}
			String beanToString = ObjectUtil.getPropertyStringToline(object.getClass(), object);
			return beanToString;
		} catch (Exception e) {
			return "Read object properties error.";
		}
	}

	public static String returnCollectionToLine(Collection<?> collection) {
		StringBuffer s = new StringBuffer();
		for (Iterator<?> iterator = collection.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			s.append(returnToLine(object)).append("    ");
		}
		return s.toString();
	}

	private static String getPropertyStringToline(Class<?> c, Object entityName) throws Exception {
		// c.getSimpleName()
		String className = c.getSimpleName();

		Field field[] = c.getDeclaredFields();
		StringBuffer sb = new StringBuffer();
		sb.append(className + "[");
		for (Field f : field) {
			f.setAccessible(true);
			if (isBaseClass(f.getType())) {
				if (!f.getName().equals("serialVersionUID")) {
					sb.append(f.getName());
					sb.append(":");
					sb.append(f.get(entityName));
					sb.append(" ");
				}
			} else {
				String name = f.getName();
				String firstLetter = name.substring(0, 1).toUpperCase();
				String getMethodName = "get" + firstLetter + name.substring(1);
				Method getMethod = c.getMethod(getMethodName, new Class[] {});
				Object value = getMethod.invoke(entityName, new Object[] {});
				if (value != null) {
					sb.append(getPropertyStringToline(value.getClass(), value));
				} else {
					sb.append(":");
					sb.append(f.get(entityName));
					sb.append(" ");
				}
			}
		}
		sb.append("]");
		if (c.getSuperclass() == null || c.getSuperclass() == java.io.Serializable.class || c.getSuperclass() == java.lang.Object.class)
			return sb.toString();
		sb.append(getPropertyStringToline(c.getSuperclass(), entityName));
		return sb.toString();
	}
}
