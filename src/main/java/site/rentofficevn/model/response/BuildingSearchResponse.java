package site.rentofficevn.model.response;

import java.math.BigDecimal;
import java.util.Date;

public class BuildingSearchResponse {
	private String name;
	private String address;
	private Integer floorArea; // diện tích sàn
	private String emptyArea; // diện tích trống
	private String rentPrice; // giá thuê
	private String serviceFee; // phí dịch vụ
	private BigDecimal brokerageFee;// phí môi giới
	private Integer numberOfBasement;
	/*private Date createdDate;
	private Date modifiedDate;
	private String createdBy;
	private String modifiedBy;*/
	/*private String rentArea; // đẩy ra dạng value1,value2*/
	private String managerName;
	private String managerPhone;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getFloorArea() {
		return floorArea;
	}

	public void setFloorArea(Integer floorArea) {
		this.floorArea = floorArea;
	}

	public String getEmptyArea() {
		return emptyArea;
	}

	public void setEmptyArea(String emptyArea) {
		this.emptyArea = emptyArea;
	}

	public String getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(String rentPrice) {
		this.rentPrice = rentPrice;
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

	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}

	public void setNumberOfBasement(Integer numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}
}
