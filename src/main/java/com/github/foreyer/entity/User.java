package com.github.foreyer.entity;

import java.io.Serializable;
import java.util.Date;
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

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 用户
 * @author jameszhou
 *
 */
@Entity(name="sys_user")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String userName;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private Integer userState;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm") //输出日期格式化
	private Date createTime;
	
	@Column(length=300)
	private String userDesc;

	/**
	 * 角色
	 */
	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.PERSIST,CascadeType.MERGE})  
    @JoinTable(name="sys_user_role",  
    joinColumns={@JoinColumn(name="user_id",referencedColumnName="id") },    
      inverseJoinColumns={ @JoinColumn(name="role_id",referencedColumnName="id")    
       }    
    )    
	private Set<Role> roles = new HashSet<Role>();
	
	/**
	 * 权限
	 */
	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.PERSIST,CascadeType.MERGE})  
    @JoinTable(name="sys_user_menu",  
    joinColumns={@JoinColumn(name="user_id",referencedColumnName="id") },    
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUserState() {
		return userState;
	}

	public void setUserState(Integer userState) {
		this.userState = userState;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	public User(Long id) {
		super();
		this.id = id;
	}
	

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long id, String userName, String password, Integer userState, Date createTime, String userDesc,
			Set<Role> roles, Set<Menu> menus) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.userState = userState;
		this.createTime = createTime;
		this.userDesc = userDesc;
		this.roles = roles;
		this.menus = menus;
	}
	
}
