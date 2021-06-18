package io.simpolor.jpa.controller;

import io.simpolor.jpa.model.StudentDto;
import io.simpolor.jpa.service.StudentService;
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

		return StudentDto.of(studentService.getAll());
	}

	@GetMapping(value="/{seq}")
	public StudentDto detail(@PathVariable Long seq) {

		return StudentDto.of(studentService.get(seq));
	}

	@PostMapping
	public void register(@RequestBody StudentDto request) {

		studentService.create(request.toEntity());
	}

	@PutMapping(value="/{seq}")
	public void modify(@PathVariable Long seq,
					   @RequestBody StudentDto request) {

		request.setSeq(seq);
		studentService.update(request.toEntity());
	}

	@DeleteMapping(value="/{seq}")
	public void delete(@PathVariable Long seq) {

		studentService.delete(seq);
	}

}
