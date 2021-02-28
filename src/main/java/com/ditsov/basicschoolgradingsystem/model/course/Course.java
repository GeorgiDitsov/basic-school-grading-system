package com.ditsov.basicschoolgradingsystem.model.course;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.ditsov.basicschoolgradingsystem.model.student.StudentCourse;

import lombok.Data;

@Entity
@Table(name = "course")
@Data
public class Course implements Serializable {

    	private static final long serialVersionUID = 794956382676083297L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_pkey")
	@SequenceGenerator(name = "course_pkey", sequenceName = "course_id_seq", allocationSize = 1)
	@Column(name = "course_id")
	private Long id;

	@Column(name = "course_name")
	private String name;

	@OneToMany(mappedBy = "course")
	private Set<StudentCourse> studentCourses;

}
