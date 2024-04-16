package site.rentofficevn.service;

import java.util.List;
import java.util.Map;

import site.rentofficevn.model.dto.BuildingDTO;
import site.rentofficevn.model.dto.RentAreaDTO;
import site.rentofficevn.model.response.BuildingSearchResponse;

public interface BuildingService {
	List<BuildingSearchResponse> getBuildingList();
	List<RentAreaDTO> getRentAreaListByBuilding(Long BuildingId);
	public void saveBuilding(BuildingDTO building);
}
