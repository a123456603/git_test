package com.penban.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyUser {
	private Integer userId;
	private int age;

	private String userName;
	
	private String[] strs = {"a", "b", "c", "d"};
	
	private char a = 'a';
	
	private Character[] characters = {'1', '2', '3', '4', '5'};
	
	private double count1 = 1.20D;
	private Double count2 = 6.89D;

	private Boolean sex;
	private boolean state;

	private int[] numbers = { 1, 2, 3, 4, 5 };

	private byte b;
	private Byte loginErrorCount;

	private List<Role> roleList = new ArrayList<Role>();

	private Set<Role> roleSet = new HashSet<Role>();

	private Map<String, List<User>> mapUser = new HashMap<String, List<User>>();
	private Map<String, List<Role>> mapRole = new HashMap<String, List<Role>>();

	public MyUser() {

	}

	public MyUser(Integer userId, String userName, int age) {

	}

	public MyUser(Integer userId, String userName, int age, Boolean sex,
			boolean state, byte b, Byte loginErrorCount) {
		this.userId = userId;
		this.userName = userName;
		this.age = age;
		this.sex = sex;
		this.state = state;
		this.b = b;
		this.loginErrorCount = loginErrorCount;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String[] getStrs() {
		return strs;
	}

	public void setStrs(String[] strs) {
		this.strs = strs;
	}

	public char getA() {
		return a;
	}

	public void setA(char a) {
		this.a = a;
	}

	public Character[] getCharacters() {
		return characters;
	}

	public void setCharacters(Character[] characters) {
		this.characters = characters;
	}

	public double getCount1() {
		return count1;
	}

	public void setCount1(double count1) {
		this.count1 = count1;
	}

	public Double getCount2() {
		return count2;
	}

	public void setCount2(Double count2) {
		this.count2 = count2;
	}

	public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public int[] getNumbers() {
		return numbers;
	}

	public void setNumbers(int[] numbers) {
		this.numbers = numbers;
	}

	public byte getB() {
		return b;
	}

	public void setB(byte b) {
		this.b = b;
	}

	public Byte getLoginErrorCount() {
		return loginErrorCount;
	}

	public void setLoginErrorCount(Byte loginErrorCount) {
		this.loginErrorCount = loginErrorCount;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public Set<Role> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
	}

	public Map<String, List<User>> getMapUser() {
		return mapUser;
	}

	public void setMapUser(Map<String, List<User>> mapUser) {
		this.mapUser = mapUser;
	}

	public Map<String, List<Role>> getMapRole() {
		return mapRole;
	}

	public void setMapRole(Map<String, List<Role>> mapRole) {
		this.mapRole = mapRole;
	}
}
