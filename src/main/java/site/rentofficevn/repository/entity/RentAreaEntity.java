package site.rentofficevn.repository.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rentarea")
public class RentAreaEntity extends BaseEntity {
	@Column(name = "value")
	private Integer value;

	@ManyToOne
	@JoinColumn(name = "buildingid", nullable = false)
	private BuildingEntity building;

	public BuildingEntity getBuilding() {
		return building;
	}

	public void setBuilding(BuildingEntity building) {
		this.building = building;
	}

	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}

}