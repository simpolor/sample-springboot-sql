package io.simpolor.mysql.controller;

import io.simpolor.mysql.model.StudentDto;
import io.simpolor.mysql.repository.entity.Student;
import io.simpolor.mysql.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/students")
@RestController
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;

	@GetMapping(value="/total-count")
	public long totalCount() {

		return studentService.getTotalCount();
	}

	@GetMapping
	public List<Student> list() {

		return studentService.getAll();
	}

	@GetMapping(value="/{seq}")
	public Student detail(@PathVariable Long seq) {

		return studentService.get(seq);
	}

	@PostMapping
	public void register(@RequestBody StudentDto studentDto) {

		studentService.register(studentDto.toEntity());
	}

	@PutMapping(value="/{seq}")
	public void modify(@PathVariable Long seq,
					   @RequestBody StudentDto studentDto) {

		studentDto.setSeq(seq);
		studentService.modify(studentDto.toEntity());
	}

	@DeleteMapping(value="/{seq}")
	public void delete(@PathVariable Long seq) {

		studentService.delete(seq);
	}
}
