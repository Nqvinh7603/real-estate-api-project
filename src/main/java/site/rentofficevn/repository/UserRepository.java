package site.rentofficevn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.rentofficevn.repository.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findOneByUserNameAndStatus(String name, int status);
}
