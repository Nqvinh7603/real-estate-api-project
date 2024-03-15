package site.rentofficevn.repository;

import site.rentofficevn.repository.entity.DistrictEntity;

public interface DistrictRepository extends JdbcRepository<DistrictEntity> {
	DistrictEntity findById(Long id);
}
