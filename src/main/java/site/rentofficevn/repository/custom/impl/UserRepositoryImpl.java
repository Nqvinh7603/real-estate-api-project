package site.rentofficevn.repository.custom.impl;

import org.springframework.stereotype.Repository;
import site.rentofficevn.repository.custom.UserRepositoryCustom;
import site.rentofficevn.repository.entity.UserEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<UserEntity> findByRole(String roleCode) {
        //JPQL
        String jqpl = "select u from UserEntity u join u.roles r where r.code = ?1";
        return entityManager.createQuery(jqpl, UserEntity.class)
                .setParameter(1, roleCode)
                .getResultList();
    }
}
