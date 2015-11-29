package com.hiberate.demo;

public class User {

	private int id;
	
	private String name;
	
	public IDCard getCard() {
		return card;
	}

	public void setCard(IDCard card) {
		this.card = card;
	}

	private IDCard card;
	private Group group;
	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	private int age;

	public int  getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}
