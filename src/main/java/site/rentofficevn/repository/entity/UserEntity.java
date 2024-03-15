package site.rentofficevn.repository.entity;

import site.rentofficevn.annotation.Column;
import site.rentofficevn.annotation.Entity;
import site.rentofficevn.annotation.Table;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {
	@Column(name = "username")
	private String userName;
	@Column(name = "password")
	private String password;
	@Column(name = "fullname")
	private String fullName;
	@Column(name = "phone")
	private String phone;
	@Column(name = "email")
	private String email;
	@Column(name = "status")
	private Integer status;
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
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getFullName() {
		return fullName;
	}
}
