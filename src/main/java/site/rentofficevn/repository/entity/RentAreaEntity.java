package site.rentofficevn.repository.entity;



import javax.persistence.*;

@Entity
@Table(name = "rentarea")
public class RentAreaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "value")
	private Integer value;

	@ManyToOne
	@JoinColumn(name = "buildingid")
	private BuildingEntity building;
	
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BuildingEntity getBuilding() {
		return building;
	}

	public void setBuilding(BuildingEntity building) {
		this.building = building;
	}
}
