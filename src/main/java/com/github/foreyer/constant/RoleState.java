package com.github.foreyer.constant;

/**
 * 角色状态
 * @author jameszhou
 *
 */
public enum RoleState {

	ON(1),OFF(0);
	
	private int state = 1;
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	private RoleState(int state) {
		this.state = state;
	}
	
}
