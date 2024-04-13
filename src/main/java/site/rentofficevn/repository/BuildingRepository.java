package site.rentofficevn.repository;

import java.util.List;


import site.rentofficevn.repository.entity.BuildingEntity;

public interface BuildingRepository {
	List<BuildingEntity> findBuilding();
}
