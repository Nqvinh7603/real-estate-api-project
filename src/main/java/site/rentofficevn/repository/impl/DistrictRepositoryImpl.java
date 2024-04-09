package site.rentofficevn.repository.impl;

import org.springframework.stereotype.Repository;

import site.rentofficevn.constant.SystemConstant;
import site.rentofficevn.repository.DistrictRepository;
import site.rentofficevn.repository.entity.DistrictEntity;

import java.util.List;

@Repository
public class DistrictRepositoryImpl extends JdbcRepositoryImpl<DistrictEntity> implements DistrictRepository {
	@Override
    public List<DistrictEntity> findAll() {
        String query = "select * from district " + SystemConstant.WHERE_ONE_EQUAL_ONE;
        return null;
    }
}
