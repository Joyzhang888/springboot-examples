package com.hong.security.repostory;

import com.hong.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName: UserRepository
 * @Description: ( JPA接口. )
 * @author hong
 * @date 2017/6/16
 * @version v1.1
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
