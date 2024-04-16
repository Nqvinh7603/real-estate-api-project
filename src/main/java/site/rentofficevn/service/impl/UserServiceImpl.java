package site.rentofficevn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.rentofficevn.model.dto.UserDTO;
import site.rentofficevn.repository.custom.RoleRepository;
import site.rentofficevn.repository.custom.UserRepository;
import site.rentofficevn.repository.entity.UserEntity;
import site.rentofficevn.service.UserService;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    /*@Override
    public List<UserDTO> findByRole(String roleCode) {
        RoleEntity roleEntity = roleRepository.findByCode(roleCode);
        List<UserEntity> userEntities = roleEntity.getUsers();
        for(UserEntity item : userEntities){
            System.out.println(item.getFullName());
        }
        return Collections.emptyList();
    }*/
    @Override
    public List<UserDTO> findByRole(String roleCode) {
       List<UserEntity> userEntities = userRepository.findByRole(roleCode);
        for(UserEntity item : userEntities){
            System.out.println(item.getFullName());
        }
        return Collections.emptyList();
    }
}
