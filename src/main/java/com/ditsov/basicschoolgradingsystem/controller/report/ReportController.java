package com.ditsov.basicschoolgradingsystem.controller.report;

import java.util.Optional;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ditsov.basicschoolgradingsystem.model.course.Course;
import com.ditsov.basicschoolgradingsystem.model.student.Student;
import com.ditsov.basicschoolgradingsystem.service.course.CourseService;
import com.ditsov.basicschoolgradingsystem.service.mark.AverageMarkService;
import com.ditsov.basicschoolgradingsystem.service.student.StudentService;
import com.ditsov.basicschoolgradingsystem.service.user.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("reports/average-mark")
@AllArgsConstructor
public class ReportController {

    	private final StudentService studentService;
	private final UserService userService;
	private final CourseService courseService;
    	private final AverageMarkService averageMarkService;

	@GetMapping("/students/{studentId}")
	@RequiresRoles(value = { "admin", "student" }, logical = Logical.OR)
	public Double getAverageMarkOfStudent(@PathVariable final Long studentId,
			@RequestParam final Optional<Long> courseId) {
    	    	Student student = studentService.findStudentById(studentId);

		userService.assertCurrentUserIsStudent(student);

		return courseId
			.map(id -> averageMarkService.getStudentAverageMarkInSingleCourse(student,
				courseService.findCourseById(id)))
			.orElseGet(() -> averageMarkService.getStudentAverageMarkInAllCourses(student));
    	}

	@GetMapping("/courses/{courseId}")
	@RequiresRoles("admin")
	public Double getAverageMarkOfCourse(@PathVariable final Long courseId) {
    	    	Course course = courseService.findCourseById(courseId);

		return averageMarkService.getAllStudentsAverageMarkInSingleCourse(course);
    	}

    	@GetMapping("/courses")
	@RequiresRoles("admin")
	public Double getAverageMarkOfAllCourses() {
    	    	return averageMarkService.getAllStudentAverageMarkInAllCourses();
	}

    	@GetMapping("/students-courses")
	@RequiresRoles("admin")
    	public Double getAverageMarkOfAllExistingStudentCoursesRelationships() {
    	    	return averageMarkService.getAllExistingStudentCoursesAverageMark();
    	}

}
