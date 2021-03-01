package com.ditsov.basicschoolgradingsystem.repository.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ditsov.basicschoolgradingsystem.model.course.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
