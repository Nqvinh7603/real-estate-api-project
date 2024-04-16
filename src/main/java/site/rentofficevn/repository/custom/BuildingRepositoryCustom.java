package site.rentofficevn.repository.custom;

import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Repository;
import site.rentofficevn.repository.entity.BuildingEntity;

public interface BuildingRepositoryCustom {
	List<BuildingEntity> findBuilding(Map<String, Object> buildingSearch, List<String> buildingTypes);

}
