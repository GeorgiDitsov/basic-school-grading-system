package com.ditsov.basicschoolgradingsystem.model.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.ditsov.basicschoolgradingsystem.model.role.Role;

import lombok.Data;

@Entity
@Table(schema = "public", name = "user")
@Data
public class User implements Serializable {

    	private static final long serialVersionUID = -2150928762685757097L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_pkey")
	@SequenceGenerator(name = "user_pkey", sequenceName = "user_id_seq", allocationSize = 1)
	@Column(name = "user_id")
	private Long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

}
