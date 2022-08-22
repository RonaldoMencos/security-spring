/**
 * 
 */
package com.oscarmencos.security.service;

import java.util.List;

import com.oscarmencos.security.model.Role;
import com.oscarmencos.security.model.User;
import com.oscarmencos.security.model.UserRole;

/**
 * @author oscar
 *
 */
public interface UserSvc {

	User saveUser(User user);
	Role saveRole(Role role);
	UserRole addRoleToUser(String username, String roleName);
	User getUser(String username);
	List<User> getUsers();
	List<UserRole> getUserRoleByIdUser(Long idUser);

}
