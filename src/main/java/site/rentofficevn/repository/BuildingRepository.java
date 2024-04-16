package site.rentofficevn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.rentofficevn.repository.custom.BuildingRepositoryCustom;
import site.rentofficevn.repository.entity.BuildingEntity;
@Repository
public interface BuildingRepository extends BuildingRepositoryCustom,JpaRepository<BuildingEntity, Long> {
}
