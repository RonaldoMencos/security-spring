/**
 * 
 */
package com.oscarmencos.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.oscarmencos.security.model.Role;
import com.oscarmencos.security.model.User;
import com.oscarmencos.security.model.UserRole;
import com.oscarmencos.security.repository.RoleRepository;
import com.oscarmencos.security.repository.UserRepository;
import com.oscarmencos.security.repository.UserRoleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author oscar
 *
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserImplSvc implements UserSvc, UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public User saveUser(User user) {
		log.info("Saving new user {} to the database",user.getName());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		log.info("Saving new role {} to the database",role.getName());
		return roleRepository.save(role);
	}

	@Override
	public UserRole addRoleToUser(String username, String roleName) {
		log.info("Adding role {} to user {}",roleName,username);
		User user = userRepository.findByUsername(username);
		Role role = roleRepository.findByName(roleName);
		UserRole userRole = new UserRole(null, role, user, new Date());
		return userRoleRepository.save(userRole);
	}

	@Override
	public User getUser(String username) {
		log.info("Fetching user {}",username);
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> getUsers() {
		log.info("Fetching all users");
		return userRepository.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			log.error("User not found in the database");
			throw new UsernameNotFoundException("User not found in the database");
		} else {
			log.info("User found in the database: {}", username);
		}
		List<UserRole> listUserRole = getUserRoleByIdUser(user.getIdUser());
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		listUserRole.forEach(userRole -> {
			authorities.add(new SimpleGrantedAuthority(userRole.getRole().getName()));
		});
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}

	@Override
	public List<UserRole> getUserRoleByIdUser(Long idUser) {
		return userRoleRepository.findByUserIdUser(idUser);
	}

}
