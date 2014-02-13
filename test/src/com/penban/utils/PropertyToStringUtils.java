package com.penban.utils;

import java.io.Serializable;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.SystemUtils;

import com.penban.entity.Role;
import com.penban.entity.User;

public class PropertyToStringUtils {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		testReturnPropertisToString();
		
		User user1 = new User(1, "jordon", 36, false);
		Set<User> users = new HashSet<User>();
		users.add(user1);
		
		Role role1 = new Role(5, "admin", 1);
		Set<Role> roles = new HashSet<Role>();
		roles.add(role1);
		
//		System.out.println(ObjectUtil.returnPropertyString(user1));
//		System.out.println(ObjectUtil.returnToLine(role1));
//		
//		System.out.println(ObjectUtil.returnCollectionToLine(users));
//		System.out.println(ObjectUtil.returnCollectionPropertyString(roles));
		
	}
	
	public static void testReturnPropertisToString() throws Exception {
		
		User user1 = new User(1, "jordon", 36, false);
		User user2 = new User(2, "micheale", 50, true);
		User user3 = new User(3, "miner", 28, false);
		
		Role role1 = new Role(5, "admin", 1);
		Role role2 = new Role(2, "admin", 2);
		Role role3 = new Role(3, "admin", 3);
		
		Set<Role> roles = new HashSet<Role>();
		roles.add(role1);
		roles.add(role2);
		roles.add(role3);
		user1.setRoles(roles);
		
		Set<User> users = new HashSet<User>();
		users.add(user1);
		users.add(user2);
		users.add(user3);
		role1.setUsers(users);
		
		reflectionPropertiesToString(user1);
	}
	
	private static final String FIELD_NAME_VALUE_SEPARATOR = "=";
	private static final String COMMA_SEPARATOR = ",";
//	private static final String CONTENT_START = "【";
	private static final String CONTENT_START = "〖Begin〗";
	private static final String CONTENT_SEPARATOR = SystemUtils.LINE_SEPARATOR;
//	private static final String CONTENT_END = "】";
	private static final String CONTENT_END = "〖End〗";
	private static final String CONTENT_NULL_TEXT = "null";
	private static final String BRACE_START = "{";
	private static final String BRACE_END = "}";
	private static final String SEQUARE_BRACKETSR_START = "[";
	private static final String SEQUARE_BRACKETS_END = "]";
	private static final String OPEN_ANGLE_BRACKETS_START = "<";
	private static final String CLOSE_ANGLE_BRACKETS_END = ">";;
	private static final String NULL_TEXT = "<null>";
	private static final String BLANK_TEXT = "   ";
	private static final String SINGLE_LINE = "Single-line";
	private static final String MULTI_LINE = "Multi-line";
	
	
	
	public static String reflectionPropertiesToString(Object obj) throws Exception {
		
		StringBuffer buffer = new StringBuffer(198);
		if (obj == null) {
			buffer.append(NULL_TEXT);
		} else {
			
			Class<?> clazz = obj.getClass();
			
			buffer.append(clazz.getName());
			buffer.append(CONTENT_START);
			
			appendFieldsIn(buffer, clazz, obj, MULTI_LINE);
			
			while (!isEndClass(clazz)) {
				clazz = clazz.getSuperclass();
				appendFieldsIn(buffer, clazz, obj, MULTI_LINE);
			}
			
			buffer.append(CONTENT_SEPARATOR);
			buffer.append(CONTENT_END);
		}
		
		
		System.out.println(buffer.toString());
		return buffer.toString();
	}

	public static void appendFieldsIn(StringBuffer buffer, Class<?> clazz, Object obj, String style) throws Exception {
		
		if (clazz.isArray()) {
			reflectionAppendArray(obj);
		} else {
			
			Field[] fields = clazz.getDeclaredFields();
			AccessibleObject.setAccessible(fields, true);
			
			for (int i = 0; i < fields.length; i++) {
				
				Field field = fields[i];
				String fieldName = field.getName();
				Object fieldValue = getValue(field, obj);
				
				if (fieldName.equals("serialVersionUID") && fieldValue instanceof Long) {
					continue;
				}
				
				if (style.equals(MULTI_LINE)) {
					buffer.append(CONTENT_SEPARATOR);
					buffer.append(BLANK_TEXT);
				}
				
				buffer.append(fieldName);
				buffer.append(FIELD_NAME_VALUE_SEPARATOR);
		        
				if (isBaseClass(fieldValue)) {
					buffer.append(fieldValue);
				}
				if (i != (fields.length - 1)) {
					buffer.append(COMMA_SEPARATOR);
				}
			}
			
		}
		
	}

	public static void reflectionAppendArray(Object obj) throws Exception {
		
	}

	public static void reflectionArrayToString(Object obj) throws Exception {
		
	}
	

	public static Object getValue(Field field, Object obj) throws Exception {
		return field.get(obj);
	}
	
	private static Boolean isBaseClass(Object value) {
		return value instanceof String  || value instanceof Integer || value instanceof Character ||
				value instanceof Double || value instanceof Boolean || value instanceof Float     ||
				value instanceof Byte   || value instanceof Short   || value instanceof Long ? true : false;
	}
	
	private static Boolean isEndClass(Class<?> clazz) {
		return clazz.getSuperclass() == null || clazz.getSuperclass() == Serializable.class ||
			   clazz.getSuperclass() == Object.class || clazz == Object.class ? true : false;
	}
	

}
