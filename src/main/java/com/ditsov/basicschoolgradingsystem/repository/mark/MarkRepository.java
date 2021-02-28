package com.ditsov.basicschoolgradingsystem.repository.mark;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ditsov.basicschoolgradingsystem.model.mark.Mark;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Long> {

    	boolean existsByStudentCourseId(final Long studentCourseId);

	@Query("SELECT mark FROM Mark mark WHERE mark.studentCourse.student.id = :studentId")
	List<Mark> findAllByStudentId(final Long studentId);

	@Query("SELECT mark FROM Mark mark WHERE mark.studentCourse.course.id = :courseId")
	List<Mark> findAllByCourseId(final Long courseId);

	@Query("SELECT mark.value FROM Mark mark WHERE mark.studentCourse.student.id = :studentId AND mark.studentCourse.course.id = :courseId")
	List<Double> findAllMarksByStudentAndCourse(final Long studentId, final Long courseId);

}
