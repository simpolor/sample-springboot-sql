package io.simpolor.h2.controller;

import io.simpolor.h2.model.StudentRequest;
import io.simpolor.h2.model.StudentResponse;
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
	public List<StudentResponse> list() {

		return StudentResponse.of(studentService.getAll());
	}

	@GetMapping(value="/{seq}")
	public Student view(@PathVariable long seq) {

		return studentService.get(seq);
	}

	@PostMapping
	public Student register(@RequestBody StudentRequest request) {

		return studentService.register(request.toEntity());
	}

	@PutMapping(value="/{seq}")
	public Student modify(@PathVariable int seq,
						  @RequestBody Student student) {

		student.setSeq(seq);
		return studentService.modify(student);
	}

	@DeleteMapping(value="/{seq}")
	public long delete(@PathVariable long seq) {

		return studentService.delete(seq);
	}

	@RequestMapping(value="/not", method=RequestMethod.GET)
	public String studentNot() {

		return "Is not a studnet";
	}


}
