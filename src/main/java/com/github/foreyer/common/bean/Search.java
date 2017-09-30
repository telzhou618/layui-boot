package com.github.foreyer.common.bean;

import java.io.Serializable;
/**
 * 关键词搜索对象
 * @author jameszhou
 *
 */
public class Search implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 字段名称
	 */
	private String name;
	/**
	 * 值
	 */
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Search(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public Search() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
