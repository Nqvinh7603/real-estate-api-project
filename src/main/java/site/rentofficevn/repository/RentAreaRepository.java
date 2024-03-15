package site.rentofficevn.repository;

import java.util.List;

import site.rentofficevn.repository.entity.RentAreaEntity;

public interface RentAreaRepository {
	List<RentAreaEntity> findByBuildingId(Long id);
}
