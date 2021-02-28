package com.ditsov.basicschoolgradingsystem.controller.mark;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ditsov.basicschoolgradingsystem.model.mark.MarkDTO;
import com.ditsov.basicschoolgradingsystem.model.mark.ModifiableMarkDTO;
import com.ditsov.basicschoolgradingsystem.model.student.StudentCourse;
import com.ditsov.basicschoolgradingsystem.service.course.CourseService;
import com.ditsov.basicschoolgradingsystem.service.mark.MarkService;
import com.ditsov.basicschoolgradingsystem.service.student.StudentCourseService;
import com.ditsov.basicschoolgradingsystem.service.student.StudentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("marks")
@AllArgsConstructor
public class MarkController {

    	private final MarkService markService;
    	private final StudentCourseService studentCourseService;
    	private final StudentService studentService;
    	private final CourseService courseService;

    	@GetMapping
	@RequiresRoles("admin")
	public List<MarkDTO> getAllMarks() {
    	    	return markService.findAllMarks();
    	}

    	@PostMapping
	@RequiresRoles("admin")
    	public MarkDTO saveMark(@Valid @RequestBody final ModifiableMarkDTO modifiableMarkDto) {
    	    	StudentCourse studentCourse = studentCourseService.findStudentCourseByStudentAndCourse(
    	    		studentService.findStudentById(modifiableMarkDto.getStudentId()),
    	    		courseService.findCourseById(modifiableMarkDto.getCourseId()));

    	    	return markService.saveMark(Optional.empty(), modifiableMarkDto.getMarkValue(), studentCourse);
    	}

    	@GetMapping("/{id}")
	@RequiresRoles("admin")
	public MarkDTO getMark(@PathVariable("id") final Long markId) {
    	    	return markService.findMark(markId);
    	}

	@PutMapping("/{id}")
	@RequiresRoles("admin")
	public MarkDTO updateMark(@PathVariable("id") final Long markId,
			@Valid @RequestBody final ModifiableMarkDTO modifiableMarkDto) {
	    	StudentCourse studentCourse = studentCourseService.findStudentCourseByStudentAndCourse(
	    		studentService.findStudentById(modifiableMarkDto.getStudentId()),
	    		courseService.findCourseById(modifiableMarkDto.getCourseId()));

		return markService.saveMark(Optional.of(markId), modifiableMarkDto.getMarkValue(), studentCourse);
	}

	@DeleteMapping("/{id}")
	@RequiresRoles("admin")
	public String deleteMark(@PathVariable("id") final Long markId)
			throws InterruptedException, ExecutionException {
	    	markService.deleteMark(markId);
		return String.format("Deleting mark with id %d is in process.", markId);
	}

}
