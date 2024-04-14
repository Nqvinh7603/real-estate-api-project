package site.rentofficevn.repository;

import site.rentofficevn.repository.entity.UserEntity;

import java.util.List;

public interface UserRepository {
    List<UserEntity> findByRole(String roleCode);

}
