package io.simpolor.mybatis.controller;

import io.simpolor.mybatis.model.StudentDto;
import io.simpolor.mybatis.service.StudentMapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mapper/students")
@RequiredArgsConstructor
public class StudentMapperController {

	private final StudentMapperService studentMapperService;

	@RequestMapping(value="/total-count", method=RequestMethod.GET)
	public Long totalCount() {

		return studentMapperService.getTotalCount();
	}

	@GetMapping
	public List<StudentDto> list() {

		return studentMapperService.getAll();
	}

	@GetMapping(value="/{seq}")
	public StudentDto detail(@PathVariable Long seq) {

		return studentMapperService.get(seq);
	}

	@PostMapping
	public void register(@RequestBody StudentDto student) {

		studentMapperService.create(student);
	}

	@PutMapping(value="/{seq}")
	public void modify(@PathVariable Long seq,
					   @RequestBody StudentDto student) {

		student.setSeq(seq);
		studentMapperService.update(student);
	}

	@DeleteMapping(value="/{seq}")
	public void delete(@PathVariable Long seq) {

		studentMapperService.delete(seq);
	}


}
