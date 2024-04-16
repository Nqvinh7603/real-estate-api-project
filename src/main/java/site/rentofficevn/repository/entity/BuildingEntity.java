package site.rentofficevn.repository.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "building")
public class BuildingEntity extends BaseEntity {

	@Column(name = "name")
	private String name;
	@Column(name = "street")
	private String street;
	@Column(name = "ward")
	private String ward;
	@Column(name = "districtid")
	private Long districtId ;
	@Column(name = "floorarea")
	private Integer floorArea;
	@Column(name = "numberofbasement")
	private Integer numberOfBasement;
	@Column(name = "rentprice")
	private Integer rentPrice;
	@Column(name = "rentpricedescription")
	private String rentPriceDescription;
	@Column(name = "managername")
	private String managerName;
	@Column(name = "managerphone")
	private String managerPhone;
	@Column(name = "servicefee")
	private String serviceFee;
	@Column(name = "brokeragefee")
	private BigDecimal brokerageFee;

	@OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
	private List<RentAreaEntity> rentAreas;

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public List<RentAreaEntity> getRentAreas() {
		return rentAreas;
	}

	public void setRentAreas(List<RentAreaEntity> rentAreas) {
		this.rentAreas = rentAreas;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	/*public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}*/
	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}
	public void setNumberOfBasement(Integer numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public Integer getFloorArea() {
		return floorArea;
	}
	public void setFloorArea(Integer floorArea) {
		this.floorArea = floorArea;
	}
	public Integer getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(Integer rentPrice) {
		this.rentPrice = rentPrice;
	}

	public String getRentPriceDescription() {
		return rentPriceDescription;
	}

	public void setRentPriceDescription(String rentPriceDescription) {
		this.rentPriceDescription = rentPriceDescription;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	public String getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}
	public BigDecimal getBrokerageFee() {
		return brokerageFee;
	}
	public void setBrokerageFee(BigDecimal brokerageFee) {
		this.brokerageFee = brokerageFee;
	}
}
