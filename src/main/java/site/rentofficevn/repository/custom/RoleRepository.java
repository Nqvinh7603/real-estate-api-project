package site.rentofficevn.repository.custom;

import site.rentofficevn.repository.entity.RoleEntity;

public interface RoleRepository {
    RoleEntity findByCode(String code);
}
