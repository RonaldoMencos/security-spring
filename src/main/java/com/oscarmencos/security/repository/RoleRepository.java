/**
 * 
 */
package com.oscarmencos.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oscarmencos.security.model.Role;

/**
 * @author oscar
 *
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);
}
