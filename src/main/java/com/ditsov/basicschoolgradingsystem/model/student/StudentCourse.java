package com.ditsov.basicschoolgradingsystem.model.student;

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
import javax.persistence.UniqueConstraint;

import com.ditsov.basicschoolgradingsystem.model.course.Course;

import lombok.Data;

@Entity
@Table(name = "student_course", uniqueConstraints = @UniqueConstraint(columnNames = { "student_id", "course_id" }))
@Data
public class StudentCourse implements Serializable {

    	private static final long serialVersionUID = -1119504371483667527L;

	@Id
	@SequenceGenerator(name = "student_course_pkey", sequenceName = "student_course_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_course_pkey")
	@Column(name = "student_course_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "student_id", nullable = false)
	private Student student;

	@ManyToOne
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;

}
