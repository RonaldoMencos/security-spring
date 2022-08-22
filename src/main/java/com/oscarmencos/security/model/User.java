/**
 * 
 */
package com.oscarmencos.security.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author oscar
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Long idUser;
	
	private String name;
	private String username;
	private String password;
	
	@Column(name = "is_active")
	private Boolean isActive;
	
}
