package com.github.foreyer.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
/**
 * 角色
 * @author jameszhou
 *
 */
@Entity(name="sys_role")
public class Role implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 角色资源
	 */
	private String resource;
	/**
	 * 角色描述
	 */
	private String roleDesc;
	/**
	 * 状态
	 */
	@Column(nullable = false)
	private Integer roleState;
	
	/**
	 * 权限
	 */
	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.PERSIST,CascadeType.MERGE})  
    @JoinTable(name="sys_role_menu",  
    joinColumns={@JoinColumn(name="role_id",referencedColumnName="id") },    
      inverseJoinColumns={ @JoinColumn(name="menu_id",referencedColumnName="id")    
       }    
    )  
	private Set<Menu> menus = new HashSet<Menu>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public Integer getRoleState() {
		return roleState;
	}
	public void setRoleState(Integer roleState) {
		this.roleState = roleState;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public Set<Menu> getMenus() {
		return menus;
	}
	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Role(Long id, String roleName, String resource, String roleDesc, Integer roleState, Set<Menu> menus) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.resource = resource;
		this.roleDesc = roleDesc;
		this.roleState = roleState;
		this.menus = menus;
	}
	
}
