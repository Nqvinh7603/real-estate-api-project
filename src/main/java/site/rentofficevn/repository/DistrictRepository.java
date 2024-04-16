package site.rentofficevn.repository;

import org.springframework.stereotype.Repository;

import site.rentofficevn.repository.entity.DistrictEntity;

import java.util.List;

public interface DistrictRepository extends JdbcRepository<DistrictEntity> {
	DistrictEntity findById(Long id);
	List<DistrictEntity> findAll();
}
