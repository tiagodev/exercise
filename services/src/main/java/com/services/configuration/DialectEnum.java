package com.services.configuration;

public enum DialectEnum {

	H2("org.hibernate.dialect.H2Dialect");

	private String className;

	private DialectEnum(String className) {
		this.className = className;
	}

	public String getClassName() {
		return className;
	}
}
