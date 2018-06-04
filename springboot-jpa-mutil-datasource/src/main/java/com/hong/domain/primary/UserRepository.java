package com.hong.domain.primary;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hong on 2017/5/1.
 */
public interface UserRepository extends JpaRepository<User,Long> {
}
