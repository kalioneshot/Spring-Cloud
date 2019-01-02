package com.kali.services.auth.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kali.services.auth.entity.User;

/**
 * User Repository.
 * 
 * @author kali
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByUsername(String username);

}
