package site.rentofficevn.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import site.rentofficevn.repository.BuildingRepository;
import site.rentofficevn.repository.entity.BuildingEntity;

import java.util.List;

@Repository
public class BulidingRepoTestImpl implements BuildingRepository{
    @Override
    public List<BuildingEntity> findBuilding() {
        return null;
    }
}
