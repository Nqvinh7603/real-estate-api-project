package site.rentofficevn.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.rentofficevn.model.response.BuildingSearchResponse;
import site.rentofficevn.repository.BuildingRepository;
import site.rentofficevn.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	private BuildingRepository buildingRespository;

	@Override
	public List<BuildingSearchResponse> getBuildingList(Map<String, Object> buildingSearch,
			List<String> buildingSearchTypes) {
		
		return null;
	}

	

}
