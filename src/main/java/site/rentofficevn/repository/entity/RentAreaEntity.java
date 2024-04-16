package site.rentofficevn.repository.entity;


import javax.persistence.*;

@Entity
@Table(name = "rentarea")
public class RentAreaEntity extends BaseEntity {
	@Column(name = "value")
	private Integer value;
	/*@Column(name = "buildingid")
	private Long buildingId;*/

	@ManyToOne
	@JoinColumn(name = "buildingid")
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
	/*public Long getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}*/
}