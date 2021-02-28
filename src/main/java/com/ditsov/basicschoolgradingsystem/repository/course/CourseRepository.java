package com.ditsov.basicschoolgradingsystem.repository.course;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ditsov.basicschoolgradingsystem.model.course.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
