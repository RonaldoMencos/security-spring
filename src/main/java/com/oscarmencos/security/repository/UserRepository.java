/**
 * 
 */
package com.oscarmencos.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oscarmencos.security.model.User;

/**
 * @author oscar
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
