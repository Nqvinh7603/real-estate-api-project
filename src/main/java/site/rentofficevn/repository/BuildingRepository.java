package site.rentofficevn.repository;

import java.util.List;
import site.rentofficevn.repository.enity.BuildingEntity;
import java.util.Map;

public interface BuildingRepository {
	List<BuildingEntity> findBuilding(Map<String, Object> buildingSearch, List<String> buildingSearchType);
}
