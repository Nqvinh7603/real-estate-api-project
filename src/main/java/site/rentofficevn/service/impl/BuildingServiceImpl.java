package site.rentofficevn.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.rentofficevn.builder.BuildingSearchBuilder;
import site.rentofficevn.converter.BuildingConverter;
import site.rentofficevn.model.response.BuildingSearchResponse;
import site.rentofficevn.repository.BuildingRepository;
import site.rentofficevn.service.BuildingService;
import site.rentofficevn.repository.entity.BuildingEntity;
import site.rentofficevn.utils.MapUtils;

@Service
public class BuildingServiceImpl implements BuildingService {
	@Autowired
	private BuildingRepository buildingRespository;

	@Autowired
	private BuildingConverter buildingConverter;

	@Override
	public List<BuildingSearchResponse> getBuildingList(Map<String, Object> buildingSearch,
			List<String> buildingTypes) {
		List<BuildingSearchResponse> results = new ArrayList<>();
		BuildingSearchBuilder buildingSearchBuilder = convertToBuildingSearchBuilder(buildingSearch, buildingTypes);
		List<BuildingEntity> buildingEntity = buildingRespository.findBuilding(buildingSearchBuilder);
		for (BuildingEntity item : buildingEntity) {
			BuildingSearchResponse buildingSearchResponse = buildingConverter.convertFromEntitytoBuildingSearchResponse(item);
			results.add(buildingSearchResponse);
		}
		return results;
	}

	private BuildingSearchBuilder convertToBuildingSearchBuilder(Map<String, Object> buildingSearch, List<String> buildingTypes) {
		BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
				.setName(MapUtils.getObject(buildingSearch, "name", String.class))
				.setFloorArea(MapUtils.getObject(buildingSearch, "floorarea", Integer.class))
				.setDistrict(MapUtils.getObject(buildingSearch, "districtcode", String.class))
				.setWard(MapUtils.getObject(buildingSearch, "ward", String.class))
				.setStreet(MapUtils.getObject(buildingSearch, "street", String.class))
				.setNumberOfBasement(MapUtils.getObject(buildingSearch, "numberofbasement", Integer.class))
				.setDirection(MapUtils.getObject(buildingSearch, "direction", String.class))
				.setLevel(MapUtils.getObject(buildingSearch, "level", String.class))
				.setRentAreaFrom(MapUtils.getObject(buildingSearch, "rentareafrom", Integer.class))
				.setRentAreaTo(MapUtils.getObject(buildingSearch, "rentareato", Integer.class))
				.setRentPriceFrom(MapUtils.getObject(buildingSearch, "rentpricefrom", Integer.class))
				.setRentPriceTo(MapUtils.getObject(buildingSearch, "rentpriceto", Integer.class))
				.setManagerName(MapUtils.getObject(buildingSearch, "managername", String.class))
				.setManagerPhone(MapUtils.getObject(buildingSearch, "managerphone", String.class))
				.setTypes(buildingTypes)
				.build();
		return buildingSearchBuilder;
	}

}
