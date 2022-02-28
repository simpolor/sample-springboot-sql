package io.simpolor.querydsl.controller;

import com.querydsl.core.QueryResults;
import io.simpolor.querydsl.model.ResultDto;
import io.simpolor.querydsl.model.StudentDto;
import io.simpolor.querydsl.repository.entity.Student;
import io.simpolor.querydsl.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;

	@GetMapping(value="/search")
	public List<StudentDto> search(StudentDto.StudentSearch search) {

		QueryResults<Student> queryResults = studentService.search(search.toEntity());

		return StudentDto.of(queryResults.getResults());
	}

	@GetMapping
	public List<StudentDto> list() {
		return StudentDto.of(studentService.getAll());
	}

	@GetMapping(value="/{studentId}")
	public StudentDto detail(@PathVariable Long studentId) {

		return StudentDto.of(studentService.get(studentId));
	}

	@PostMapping
	public ResultDto register(@RequestBody StudentDto request) {

		Student student = studentService.create(request.toEntity());

		return ResultDto.of(student.getStudentId());
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

