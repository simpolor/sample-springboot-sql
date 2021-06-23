package io.simpolor.querydsl.controller;

import com.querydsl.core.QueryResults;
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

	@RequestMapping(value="/total-count", method=RequestMethod.GET)
	public long totalCount() {

		return studentService.getTotalCount();
	}

	@GetMapping
	public List<StudentDto> list() {
		return StudentDto.of(studentService.getAll());
	}

	@GetMapping(value="/search")
	public List<StudentDto> search(@RequestParam String name) {

		QueryResults<Student> queryResults = studentService.search(name);

		return StudentDto.of(queryResults.getResults());
	}

	@GetMapping(value="/{seq}")
	public StudentDto get(@PathVariable Long seq) {

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

