package com.laptrinhjavaweb.api;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.model.dto.BuildingDTO;
import com.laptrinhjavaweb.model.response.BuildingSearchResponse;
import com.laptrinhjavaweb.service.BuildingService;

@RestController
@RequestMapping("/api/building")
public class BuildingAPI {
	@Autowired
	private BuildingService buildingService;

	// Lấy chi tiết tòa nhà
	@GetMapping("/{buildingid}")
	public BuildingDTO getBuildingDetail(@PathVariable(value = "buildingid") Long buildingId) {
		System.out.println(buildingId);
		return null;
	}

	// excercise-2
	@GetMapping
	public List<BuildingSearchResponse> findBuilding(@RequestParam(required = false) Map<String, Object> buildingSearch,
			@RequestParam(required = false) List<String> buildingSearchType) throws SQLException {

		return buildingService.getBuildingList(buildingSearch, buildingSearchType);
	}
}
