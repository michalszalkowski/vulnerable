package com.michalszalkowski.vulnerable.core.bean;

public class Dog {

	private final String name;

	public Dog(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
