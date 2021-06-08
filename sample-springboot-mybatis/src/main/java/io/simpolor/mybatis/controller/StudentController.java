package io.simpolor.mybatis.controller;

import io.simpolor.mybatis.model.StudentDto;
import io.simpolor.mybatis.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/students")
@RestController
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;

	@GetMapping(value="/total-count")
	public Long totalCount() {

		return studentService.getTotalCount();
	}

	@GetMapping
	public List<StudentDto> list() {

		return studentService.getAll();
	}

	@GetMapping(value="/{seq}")
	public StudentDto detail(@PathVariable long seq) {

		return studentService.get(seq);
	}

	@PostMapping
	public void register(@RequestBody StudentDto student) {

		studentService.create(student);
	}

	@PutMapping(value="/{seq}")
	public void modify(@PathVariable int seq,
								 @RequestBody StudentDto student) {
		student.setSeq(seq);
		studentService.update(student);
	}

	@DeleteMapping(value="/{seq}")
	public void delete(@PathVariable long seq) {
		studentService.delete(seq);

	}


}
