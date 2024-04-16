package site.rentofficevn.repository.custom.impl;

import org.springframework.stereotype.Repository;
import site.rentofficevn.constant.SystemConstant;
import site.rentofficevn.repository.custom.DistrictRepositoryCustom;
import site.rentofficevn.repository.entity.DistrictEntity;

import java.util.List;


@Repository
public class DistrictRepositoryImpl implements DistrictRepositoryCustom {
    @Override
    public List<DistrictEntity> findAll() {
        String query = "select * from district " + SystemConstant.WHERE_ONE_EQUAL_ONE;
        return null;
    }
}
