package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.response.BuildingSearchResponse;

public interface BuildingService {
	List<BuildingSearchResponse> findAll();
}
