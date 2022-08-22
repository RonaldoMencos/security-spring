/**
 * 
 */
package com.oscarmencos.security.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name = "user_role")
public class UserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user_role")
	private Long idUserRole;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="role")
  	private Role role;

  	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user")
    private User user;
  	
  	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_created")
	private Date dateCreated;
}
