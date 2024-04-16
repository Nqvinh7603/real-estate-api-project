package site.rentofficevn.repository.custom;

import site.rentofficevn.repository.JdbcRepository;
import site.rentofficevn.repository.entity.DistrictEntity;

public interface DistrictRepositoryCustom extends JdbcRepository<DistrictEntity> {
	DistrictEntity findById(Long id);
}
