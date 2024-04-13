package site.rentofficevn.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import site.rentofficevn.converter.BuildingConverter;
import site.rentofficevn.model.response.BuildingSearchResponse;
import site.rentofficevn.repository.BuildingRepository;
import site.rentofficevn.service.BuildingService;
import site.rentofficevn.repository.entity.BuildingEntity;

@Service
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	@Qualifier("buildingRepositoryImpl")
	private BuildingRepository buildingRepository;
	@Autowired
	private BuildingConverter buildingConverter;

	@Override
	public List<BuildingSearchResponse> getBuildingList() {
		List<BuildingSearchResponse> results = new ArrayList<>();
		List<BuildingEntity> buildingEntity = buildingRepository.findBuilding();
		for (BuildingEntity item : buildingEntity) {
			BuildingSearchResponse buildingSearchResponse = buildingConverter.convertFromEntitytoBuildingSearchResponse(item);
			results.add(buildingSearchResponse);
		}
		return results;
	}

}
