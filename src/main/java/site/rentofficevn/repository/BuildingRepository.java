package site.rentofficevn.repository;

import java.util.List;
import java.util.Map;

import site.rentofficevn.repository.entity.BuildingEntity;

public interface BuildingRepository {
	List<BuildingEntity> findBuilding(Map<String, Object> buildingSearch, List<String> buildingSearchType);
}
