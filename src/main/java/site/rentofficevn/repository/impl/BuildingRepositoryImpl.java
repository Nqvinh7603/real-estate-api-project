package site.rentofficevn.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import site.rentofficevn.repository.BuildingRepository;
import site.rentofficevn.repository.entity.BuildingEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Repository
public class BuildingRepositoryImpl implements BuildingRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BuildingEntity> findBuilding() {
        //JPQL
        /*String sql = "FROM BuildingEntity";
        Query query = entityManager.createQuery(sql, BuildingEntity.class);*/

        //SQl Native
        String sql = "Select * from building";
        Query query = entityManager.createNativeQuery(sql, BuildingEntity.class);
        return query.getResultList();
    }

    @Override
    public BuildingEntity findById(Long id) {
        String sql = "Select * from building b where b.id = " + id+"";
        Query query = entityManager.createNativeQuery(sql, BuildingEntity.class);
        return (BuildingEntity) query.getSingleResult();
    }
}
