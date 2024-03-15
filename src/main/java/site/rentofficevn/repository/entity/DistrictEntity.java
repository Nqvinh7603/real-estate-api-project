package site.rentofficevn.repository.entity;

import site.rentofficevn.annotation.Column;
import site.rentofficevn.annotation.Entity;
import site.rentofficevn.annotation.Table;

@Entity
@Table(name="district")
public class DistrictEntity extends BaseEntity{
	@Column(name = "code")
	private String code;
	@Column(name = "name")
	private String name;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
