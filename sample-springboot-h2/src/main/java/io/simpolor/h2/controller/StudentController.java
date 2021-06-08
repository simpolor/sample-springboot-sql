package io.simpolor.h2.controller;

import io.simpolor.h2.model.StudentDto;
import io.simpolor.h2.repository.entity.Student;
import io.simpolor.h2.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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

	@GetMapping(value="/{seq}")
	public StudentDto detail(@PathVariable long seq) {

		Student student = studentService.get(seq);
		if(Objects.isNull(student)){
			return null;
		}

		return StudentDto.of(student);
	}

	@PostMapping
	public void register(@RequestBody StudentDto studentDto) {

		studentService.create(studentDto.toEntity());
	}

	@PutMapping(value="/{seq}")
	public void modify(@PathVariable int seq,
					   @RequestBody StudentDto studentDto) {

		studentDto.setSeq(seq);
		studentService.update(studentDto.toEntity());
	}

	@DeleteMapping(value="/{seq}")
	public void delete(@PathVariable long seq) {

		studentService.delete(seq);
	}

	@RequestMapping(value="/not", method=RequestMethod.GET)
	public String studentNot() {

		return "Is not a studnet";
	}


}
