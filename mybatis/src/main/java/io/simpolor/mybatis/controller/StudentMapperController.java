package io.simpolor.mybatis.controller;

import io.simpolor.mybatis.model.ResultDto;
import io.simpolor.mybatis.model.StudentDto;
import io.simpolor.mybatis.repository.entity.Student;
import io.simpolor.mybatis.service.StudentMapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/mapper/students")
@RequiredArgsConstructor
public class StudentMapperController {

	private final StudentMapperService studentMapperService;

	@GetMapping
	public List<StudentDto> list() {

		List<Student> students = studentMapperService.getAll();
		if(CollectionUtils.isEmpty(students)){
			return Collections.EMPTY_LIST;
		}

		return StudentDto.of(students);
	}

	@GetMapping(value="/{studentId}")
	public StudentDto detail(@PathVariable Long studentId) {

		Student student = studentMapperService.get(studentId);

		return StudentDto.of(student);
	}

	@PostMapping
	public ResultDto register(@RequestBody StudentDto request) {

		Student student = studentMapperService.create(request.toEntity());

		return ResultDto.of(student.getStudentId());
	}

	@PutMapping(value="/{studentId}")
	public void modify(@PathVariable Long studentId,
					   @RequestBody StudentDto request) {

		request.setId(studentId);
		studentMapperService.update(request.toEntity());
	}

	@DeleteMapping(value="/{studentId}")
	public void delete(@PathVariable Long studentId) {

		studentMapperService.delete(studentId);
	}


}
