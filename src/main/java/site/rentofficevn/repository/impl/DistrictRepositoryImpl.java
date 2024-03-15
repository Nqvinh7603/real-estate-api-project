package site.rentofficevn.repository.impl;

import org.springframework.stereotype.Repository;

import site.rentofficevn.repository.DistrictRepository;
import site.rentofficevn.repository.entity.DistrictEntity;
@Repository
public class DistrictRepositoryImpl extends JdbcRepositoryImpl<DistrictEntity> implements DistrictRepository {
	
}
