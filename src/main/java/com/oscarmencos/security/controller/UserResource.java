/**
 * 
 */
package com.oscarmencos.security.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.oscarmencos.security.model.Role;
import com.oscarmencos.security.model.User;
import com.oscarmencos.security.model.UserRole;
import com.oscarmencos.security.service.UserSvc;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author oscar
 *
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {

	@Autowired
	private UserSvc userSvc;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers() {
		return ResponseEntity.ok().body(userSvc.getUsers());
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users").toUriString());
		return ResponseEntity.created(uri).body(userSvc.saveUser(user));
	}
	
	@PostMapping("/roles")
	public ResponseEntity<Role> saveRole(@RequestBody Role role) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/roles").toUriString());
		return ResponseEntity.created(uri).body(userSvc.saveRole(role));
	}
	
	@PostMapping("/user-roles")
	public ResponseEntity<UserRole> saveUserRole(@RequestBody RoleToUserForm form) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user-roles").toUriString());
		return ResponseEntity.created(uri).body(userSvc.addRoleToUser(form.getUsername(), form.getRoleName()));
	}
	
	@Data
	class RoleToUserForm {
		private String username;
		private String roleName;
	}
}
