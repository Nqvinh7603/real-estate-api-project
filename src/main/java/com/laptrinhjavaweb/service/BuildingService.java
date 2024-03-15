package com.laptrinhjavaweb.service;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.model.response.BuildingSearchResponse;

public interface BuildingService {
	List<BuildingSearchResponse> getBuildingList(Map<String, Object> buildingSearch, List<String> buildingSearchTypes);
}
