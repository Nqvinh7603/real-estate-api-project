package site.rentofficevn.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import site.rentofficevn.converter.BuildingConverter;
import site.rentofficevn.converter.RentAreaConverter;
import site.rentofficevn.model.dto.BuildingDTO;
import site.rentofficevn.model.dto.RentAreaDTO;
import site.rentofficevn.model.response.BuildingSearchResponse;
import site.rentofficevn.repository.BuildingRepository;
import site.rentofficevn.repository.custom.RentAreaRepository;
import site.rentofficevn.repository.entity.RentAreaEntity;
import site.rentofficevn.service.BuildingService;
import site.rentofficevn.repository.entity.BuildingEntity;

@Service
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	private BuildingRepository buildingRepository;
	@Autowired
	private BuildingConverter buildingConverter;
	@Autowired
	private RentAreaConverter rentAreaConverter;
	@Autowired
	private RentAreaRepository rentAreaRepository;

	/*@Override
	public List<BuildingSearchResponse> getBuildingList() {
		List<BuildingSearchResponse> results = new ArrayList<>();
		List<BuildingEntity> buildingEntity = buildingRepository.findBuilding();
		for (BuildingEntity item : buildingEntity) {
			BuildingSearchResponse buildingSearchResponse = buildingConverter.convertFromEntitytoBuildingSearchResponse(item);
			results.add(buildingSearchResponse);
		}
		return results;
	}*/

	@Override
	public List<BuildingSearchResponse> getBuildingList() {
		return Collections.emptyList();
	}

	@Override
	public List<RentAreaDTO> getRentAreaListByBuilding(Long buildingId) {
		List<RentAreaDTO> results = new ArrayList<>();
		BuildingEntity buildingEntity = buildingRepository.findById(buildingId).get();
		List<RentAreaEntity> rentAreaEntities = rentAreaRepository.findByBuildingId(buildingId);
		results = rentAreaEntities.stream().map(item -> rentAreaConverter.convertFromEntitytoRentAreaDTO(item)).collect(Collectors.toList());
		return results;
	}

	@Override
	@Transactional
	public void saveBuilding(BuildingDTO building) {
		BuildingEntity buildingEntity = null;
		buildingRepository.save(buildingEntity);
	}
}
