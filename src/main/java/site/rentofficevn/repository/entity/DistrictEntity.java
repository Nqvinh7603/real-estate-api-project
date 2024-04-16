package site.rentofficevn.repository.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="district")
public class DistrictEntity extends BaseEntity{

	@Column(name = "code")
	private String code;
	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "district", fetch = FetchType.LAZY)
	private List<BuildingEntity> buildings;

	public List<BuildingEntity> getBuildings() {
		return buildings;
	}

	public void setBuildings(List<BuildingEntity> buildings) {
		this.buildings = buildings;
	}

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
