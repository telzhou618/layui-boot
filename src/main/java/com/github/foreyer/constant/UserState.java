package com.github.foreyer.constant;

/**
 * 用户状态
 * @author jameszhou
 *
 */
public enum UserState {

	ON(1),OFF(0);
	
	private int state = 1;
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	private UserState(int state) {
		this.state = state;
	}
	
}
