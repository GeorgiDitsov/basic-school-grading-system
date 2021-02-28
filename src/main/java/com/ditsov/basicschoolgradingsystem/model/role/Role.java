package com.ditsov.basicschoolgradingsystem.model.role;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "role")
@Data
public class Role implements Serializable {

    	private static final long serialVersionUID = -3670049116724335575L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_pkey")
	@SequenceGenerator(name = "role_pkey", sequenceName = "role_id_seq", allocationSize = 1)
	@Column(name = "role_id")
	private Long id;

	@Column(name = "role_name", nullable = false, unique = true)
	private String name;

}
