package site.rentofficevn.repository.custom.impl;

import org.springframework.stereotype.Repository;

import site.rentofficevn.repository.custom.DistrictRepositoryCustom;
import site.rentofficevn.repository.entity.DistrictEntity;
@Repository
public class DistrictRepositoryImpl extends JdbcRepositoryImpl<DistrictEntity> implements DistrictRepositoryCustom {
	
}
