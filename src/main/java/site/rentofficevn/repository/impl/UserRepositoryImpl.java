package site.rentofficevn.repository.impl;

import org.springframework.stereotype.Repository;
import site.rentofficevn.repository.UserRepository;
import site.rentofficevn.repository.entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<UserEntity> findByRole(String roleCode) {
        //SQL Native

        return Collections.emptyList();
    }
}
