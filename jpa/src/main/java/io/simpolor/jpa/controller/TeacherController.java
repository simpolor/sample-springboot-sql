package io.simpolor.jpa.controller;

import io.simpolor.jpa.model.TeacherDto;
import io.simpolor.jpa.repository.entity.Teacher;
import io.simpolor.jpa.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/teachers")
@RestController
@RequiredArgsConstructor
public class TeacherController {

	private final TeacherService teacherService;


	@GetMapping
	public List<TeacherDto> list() {

		List<Teacher> teachers = teacherService.getAll();
		if(CollectionUtils.isEmpty(teachers)){
			return Collections.EMPTY_LIST;
		}

		return teachers.stream()
				.map(TeacherDto::of)
				.collect(Collectors.toList());
	}

	@GetMapping(value="/{teacherId}")
	public TeacherDto detail(@PathVariable Long teacherId) {

		return TeacherDto.of(teacherService.get(teacherId));
	}

	@PostMapping
	public void register(@RequestBody TeacherDto request) {

		teacherService.create(request.toEntity());
	}

	@PutMapping(value="/{teacherId}")
	public void modify(@PathVariable Long teacherId,
					@RequestBody TeacherDto request) {

		request.setId(teacherId);
		teacherService.update(request.toEntity());
	}

	@DeleteMapping(value="/{teacherId}")
	public void delete(@PathVariable Long teacherId) {

		teacherService.delete(teacherId);
	}

}
