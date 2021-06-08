package io.simpolor.jdbc.controller;

import io.simpolor.jdbc.model.StudentDto;
import io.simpolor.jdbc.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;

	@GetMapping(value="/total-count")
	public long totalCount() {

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
	public void register(@RequestBody StudentDto studentDto) {

		studentService.register(studentDto);
	}

	@PutMapping(value="/{seq}")
	public void modify(@PathVariable int seq, @RequestBody StudentDto studentDto) {

		studentDto.setSeq(seq);
		studentService.modify(studentDto);
	}

	@DeleteMapping(value="/{seq}")
	public void delete(@PathVariable long seq) {

		studentService.delete(seq);
	}


}
