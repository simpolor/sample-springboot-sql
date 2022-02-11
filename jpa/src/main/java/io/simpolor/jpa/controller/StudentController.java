package io.simpolor.jpa.controller;

import io.simpolor.jpa.model.ResultDto;
import io.simpolor.jpa.model.StudentDto;
import io.simpolor.jpa.repository.entity.Student;
import io.simpolor.jpa.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/students")
@RestController
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;

	@GetMapping
	public List<StudentDto> list() {

		List<Student> students = studentService.getAll();
		if(CollectionUtils.isEmpty(students)){
			return Collections.EMPTY_LIST;
		}

		return students.stream()
				.map(StudentDto::of)
				.collect(Collectors.toList());
	}

	@GetMapping(value="/{studentId}")
	public StudentDto.StudentDetail detail(@PathVariable Long studentId) {

		Student student = studentService.get(studentId);

		return StudentDto.StudentDetail.of(student);
	}

	@PostMapping
	public ResultDto register(@RequestBody StudentDto request) {

		Student student = studentService.create(request.toEntity());

		return ResultDto.builder()
				.id(student.getStudentId())
				.build();
	}

	@PutMapping(value="/{studentId}")
	public void modify(@PathVariable Long studentId,
					   @RequestBody StudentDto request) {

		request.setId(studentId);
		studentService.update(request.toEntity());
	}

	@DeleteMapping(value="/{studentId}")
	public void delete(@PathVariable Long studentId) {

		studentService.delete(studentId);
	}

}
