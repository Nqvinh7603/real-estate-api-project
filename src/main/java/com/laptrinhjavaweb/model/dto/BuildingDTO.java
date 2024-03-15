package com.laptrinhjavaweb.model.dto;

public class BuildingDTO {
	private Long id;
	private String name;
	private Integer floorArea;
	private Long[] buildingIds;
	private Integer numberOfBasement;
	public Long[] getBuildingIds() {
		return buildingIds;
	}
	public void setBuildingIds(Long[] buildingIds) {
		this.buildingIds = buildingIds;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}
	public void setNumberOfBasement(Integer numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}
	
}
