package site.rentofficevn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import site.rentofficevn.repository.custom.DistrictRepositoryCustom;
import site.rentofficevn.repository.entity.DistrictEntity;



public interface DistrictRepository extends DistrictRepositoryCustom,JpaRepository<DistrictEntity, Long> {
}
