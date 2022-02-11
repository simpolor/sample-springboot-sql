package io.simpolor.jpa.controller;

import io.simpolor.jpa.model.ClassroomDto;
import io.simpolor.jpa.model.ResultDto;
import io.simpolor.jpa.repository.entity.Classroom;
import io.simpolor.jpa.service.ClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/classrooms")
@RestController
@RequiredArgsConstructor
public class ClassroomController {

	private final ClassroomService classroomService;

	@GetMapping
	public List<ClassroomDto> list() {

		List<Classroom> classrooms = classroomService.getAll();
		if(CollectionUtils.isEmpty(classrooms)){
			return Collections.EMPTY_LIST;
		}

		return classrooms.stream()
				.map(ClassroomDto::of)
				.collect(Collectors.toList());
	}

	@GetMapping(value="/{classroomId}")
	public ClassroomDto.ClassroomDetail detail(@PathVariable Long classroomId) {

		Classroom classroom = classroomService.get(classroomId);

		return ClassroomDto.ClassroomDetail.of(classroom);
	}

	@PostMapping
	public ResultDto register(@RequestBody ClassroomDto request) {

		Classroom classroom = classroomService.create(request.toEntity());

		return ResultDto.of(classroom.getClassroomId());
	}

	@PutMapping(value="/{classroomId}")
	public void modify(@PathVariable Long classroomId,
							@RequestBody ClassroomDto request) {

		request.setId(classroomId);
		classroomService.update(request.toEntity());
	}

	@DeleteMapping(value="/{classroomId}")
	public void delete(@PathVariable Long classroomId) {

		classroomService.delete(classroomId);
	}

}
