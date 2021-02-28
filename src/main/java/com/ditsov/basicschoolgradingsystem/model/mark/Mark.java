package com.ditsov.basicschoolgradingsystem.model.mark;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.ditsov.basicschoolgradingsystem.model.student.StudentCourse;

import lombok.Data;

@Entity
@Table(name = "mark")
@DynamicInsert
@DynamicUpdate
@Data
public class Mark implements Serializable {

    	private static final long serialVersionUID = -8884702114970137268L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mark_pkey")
	@SequenceGenerator(name = "mark_pkey", sequenceName = "mark_id_seq", allocationSize = 1)
	@Column(name = "mark_id")
	private Long id;

	@Column(name = "mark", nullable = false)
	private Double value;

	@CreationTimestamp
	@Column(name = "mark_date", nullable = false)
	private LocalDateTime date;

	@ManyToOne
	@JoinColumn(name = "student_course_id", nullable = false)
	private StudentCourse studentCourse;

}
