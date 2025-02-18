package io.simpolor.h2.controller;

import io.simpolor.h2.model.ResultDto;
import io.simpolor.h2.model.StudentDto;
import io.simpolor.h2.repository.entity.Student;
import io.simpolor.h2.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/students")
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;

	@GetMapping(value="/total-count")
	public Long totalCount() {

		return studentService.getTotalCount();
	}

	@GetMapping
	public List<StudentDto> list() {

		return StudentDto.of(studentService.getAll());
	}

	@GetMapping(value="/{studentId}")
	public StudentDto detail(@PathVariable Long studentId) {

		Student student = studentService.get(studentId);

		return StudentDto.of(student);
	}

	@PostMapping
	public ResultDto register(@RequestBody StudentDto studentDto) {

		Student student = studentService.create(studentDto.toEntity());

		return ResultDto.of(student.getStudentId());
	}

	@PutMapping(value="/{studentId}")
	public void modify(@PathVariable Long studentId,
					   @RequestBody StudentDto studentDto) {

		studentDto.setId(studentId);
		studentService.update(studentDto.toEntity());
	}

	@DeleteMapping(value="/{studentId}")
	public void delete(@PathVariable Long studentId) {

		studentService.delete(studentId);
	}

	@RequestMapping(value="/not", method=RequestMethod.GET)
	public String studentNot() {

		return "Is not a student";
	}


}
