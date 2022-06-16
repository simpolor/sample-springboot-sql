package io.simpolor.masterslave.controller;

import io.simpolor.masterslave.model.ResultDto;
import io.simpolor.masterslave.model.StudentDto;
import io.simpolor.masterslave.repository.entity.Student;
import io.simpolor.masterslave.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/students")
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;

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

}
