package site.rentofficevn.repository.custom;

import java.util.List;


import site.rentofficevn.repository.entity.BuildingEntity;

public interface BuildingRepository{
	List<BuildingEntity> findBuilding();
	BuildingEntity findById(Long id);
	void save(BuildingEntity buildingEntity);
}
