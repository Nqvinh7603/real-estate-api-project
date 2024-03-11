package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.dto.response.BuildingSearchResponse;

import com.laptrinhjavaweb.repository.enity.BuildingEntity;

public interface BuildingService {
	List<BuildingSearchResponse> findAll();
}
