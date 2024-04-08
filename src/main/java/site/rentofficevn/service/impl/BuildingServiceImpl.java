package site.rentofficevn.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.rentofficevn.converter.BuildingConverter;
import site.rentofficevn.model.response.BuildingSearchResponse;
import site.rentofficevn.repository.BuildingRepository;
import site.rentofficevn.repository.impl.BuildingRepositoryImpl;
import site.rentofficevn.service.BuildingService;
import site.rentofficevn.repository.entity.BuildingEntity;

@Service
public class BuildingServiceImpl implements BuildingService {
	@Autowired
	private BuildingRepository buildingRespository ;


	private BuildingConverter buildingConverter = new BuildingConverter();

	@Override
	public List<BuildingSearchResponse> getBuildingList(Map<String, Object> buildingSearch,
			List<String> buildingSearchTypes) {
		List<BuildingSearchResponse> results = new ArrayList<>();
		List<BuildingEntity> buildingEntity = buildingRespository.findBuilding(buildingSearch, buildingSearchTypes);
		for (BuildingEntity item : buildingEntity) {
			BuildingSearchResponse buildingSearchResponse = buildingConverter.convertFromEntitytoBuildingSearchResponse(item);
			results.add(buildingSearchResponse);
		}
		return results;
	}

}
