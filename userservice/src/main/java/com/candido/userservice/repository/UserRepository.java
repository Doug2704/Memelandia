package com.candido.userservice.repository;

import com.candido.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Douglas Candido
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
