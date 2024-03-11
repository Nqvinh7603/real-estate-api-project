package com.laptrinhjavaweb.repository;

import java.util.List;

import com.laptrinhjavaweb.repository.enity.BuildingEntity;

public interface BuildingRepository {
	List<BuildingEntity> findAll();
}
