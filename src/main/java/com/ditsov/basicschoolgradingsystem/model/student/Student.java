package com.ditsov.basicschoolgradingsystem.model.student;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.ditsov.basicschoolgradingsystem.model.user.User;

import lombok.Data;

@Entity
@Table(name = "student")
@Data
public class Student implements Serializable {

    	private static final long serialVersionUID = 3124329071648921398L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_pkey")
	@SequenceGenerator(name = "student_pkey", sequenceName = "student_id_seq", allocationSize = 1)
	@Column(name = "student_id")
	private Long id;

	@Column(name = "student_name")
	private String name;

	@OneToMany(mappedBy = "student")
	private Set<StudentCourse> studentCourses;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", unique = true)
	private User user;

}
