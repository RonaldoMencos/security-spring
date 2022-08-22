/**
 * 
 */
package com.oscarmencos.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oscarmencos.security.model.UserRole;

/**
 * @author oscar
 *
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	List<UserRole> findByUserIdUser(Long idUser);
}
