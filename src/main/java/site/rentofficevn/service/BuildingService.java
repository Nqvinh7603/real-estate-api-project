package site.rentofficevn.service;

import java.util.List;
import java.util.Map;

import site.rentofficevn.model.response.BuildingSearchResponse;

public interface BuildingService {
	List<BuildingSearchResponse> getBuildingList(Map<String, String> buildingSearch, List<String> buildingTypes);
}
