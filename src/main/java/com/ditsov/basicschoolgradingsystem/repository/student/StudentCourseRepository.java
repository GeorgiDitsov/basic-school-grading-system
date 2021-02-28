package com.ditsov.basicschoolgradingsystem.repository.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ditsov.basicschoolgradingsystem.model.student.StudentCourse;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {

    	Optional<StudentCourse> findDistinctByStudentIdAndCourseId(final Long studentId, final Long courseId);

}
