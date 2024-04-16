package site.rentofficevn.repository.custom.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import site.rentofficevn.repository.custom.BuildingRepositoryCustom;
import site.rentofficevn.repository.entity.BuildingEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

    /*@PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BuildingEntity> findBuilding() {
        //JPQL
        *//*String sql = "FROM BuildingEntity";
        Query query = entityManager.createQuery(sql, BuildingEntity.class);*//*

        //SQl Native
        String sql = "Select * from building";
        Query query = entityManager.createNativeQuery(sql, BuildingEntity.class);
        return query.getResultList();
    }

    @Override
    public BuildingEntity findById(Long id) {
        String sql = "Select * from building b where b.id = "+id+"";
        Query query = entityManager.createNativeQuery(sql, BuildingEntity.class);
        return (BuildingEntity) query.getSingleResult();
    }

    @Override
    @Transactional
    public void save(BuildingEntity buildingEntity) {
        entityManager.persist(buildingEntity);
    }*/
}
