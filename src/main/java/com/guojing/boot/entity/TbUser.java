package com.guojing.boot.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by xingsen on 15/2/5.
 */
@Entity
@Table(name = "tb_user")
public class TbUser {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date createTime;
	@Column(nullable = false)
	private String loginName;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String username;
	private String salt;  //盐值


	/**
	 * 创建人id
	 */
	private Long creator;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}
}
