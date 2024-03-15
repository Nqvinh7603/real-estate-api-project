package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.model.response.BuildingSearchResponse;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.enity.BuildingEntity;
import com.laptrinhjavaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	private BuildingRepository buildingRespository;

	@Override
	public List<BuildingSearchResponse> findAll() {
		List<BuildingSearchResponse> results = new ArrayList<>();
		List<BuildingEntity> buildingEntities = buildingRespository.findAll();
		for (BuildingEntity item : buildingEntities) {
			BuildingSearchResponse building = new BuildingSearchResponse();
			building.setName(item.getName());
			building.setAddress(item.getStreet() + " - " + item.getWard());
			results.add(building);
		}

		return results;
	}

}
