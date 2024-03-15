package site.rentofficevn.model.request;

import java.util.Date;
import java.util.List;

import site.rentofficevn.model.dto.BaseDTO;
public class BuildingSearchRequest extends BaseDTO{
	private String name; // Tên tòa nhà
	private Integer floorArea;  // diện tích sàn
	private String districtCode;  // quận theo Code
	private String ward;   // phường
	private String street; // đường
	private Integer numberOfBasement; //  số tầng hầm
	private String direction; // hướng
	private String level;
	private Integer rentAreaFrom;    // diện tích từ
	private Integer rentAreaTo;
	private Integer rentPriceFrom;   // giá thuê từ 
	private Integer rentPriceTo;   // giá thuê đến 	
	private Integer areaRentTo; 
	private Integer staffId;    // nhân viên phụ trách
	private List<String> rentTypes;  // loại tòa nhà
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
	public Integer getFloorArea() {
		return floorArea;
	}
	public void setFloorArea(Integer floorArea) {
		this.floorArea = floorArea;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}
	public void setNumberOfBasement(Integer numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Integer getRentAreaFrom() {
		return rentAreaFrom;
	}
	public void setRentAreaFrom(Integer rentAreaFrom) {
		this.rentAreaFrom = rentAreaFrom;
	}
	public Integer getRentAreaTo() {
		return rentAreaTo;
	}
	public void setRentAreaTo(Integer rentAreaTo) {
		this.rentAreaTo = rentAreaTo;
	}
	public Integer getRentPriceFrom() {
		return rentPriceFrom;
	}
	public void setRentPriceFrom(Integer rentPriceFrom) {
		this.rentPriceFrom = rentPriceFrom;
	}
	public Integer getRentPriceTo() {
		return rentPriceTo;
	}
	public void setRentPriceTo(Integer rentPriceTo) {
		this.rentPriceTo = rentPriceTo;
	}
	public Integer getAreaRentTo() {
		return areaRentTo;
	}
	public void setAreaRentTo(Integer areaRentTo) {
		this.areaRentTo = areaRentTo;
	}
	public Integer getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	public List<String> getRentTypes() {
		return rentTypes;
	}
	public void setRentTypes(List<String> rentTypes) {
		this.rentTypes = rentTypes;
	}
}
