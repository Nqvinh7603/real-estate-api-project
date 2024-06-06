package site.rentofficevn.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import site.rentofficevn.model.dto.MyUser;
import site.rentofficevn.repository.UserRepository;
import site.rentofficevn.repository.entity.RoleEntity;
import site.rentofficevn.repository.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findOneByUserNameAndStatus(username, 1);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleEntity item: userEntity.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+item.getCode()));
        }
        MyUser myUserDetail = new MyUser(userEntity.getUserName(), userEntity.getPassword(), true, true, true, true, authorities);
        BeanUtils.copyProperties(userEntity, myUserDetail);
        return myUserDetail;
    }
}
