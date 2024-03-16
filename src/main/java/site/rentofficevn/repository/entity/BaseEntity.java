package site.rentofficevn.repository.entity;

import site.rentofficevn.annotation.Column;
import site.rentofficevn.annotation.Entity;
import site.rentofficevn.annotation.Table;

@Entity
@Table
public class BaseEntity {
	@Column(name = "id")
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}