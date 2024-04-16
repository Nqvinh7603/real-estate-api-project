package site.rentofficevn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.rentofficevn.repository.entity.BuildingEntity;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>{
}
