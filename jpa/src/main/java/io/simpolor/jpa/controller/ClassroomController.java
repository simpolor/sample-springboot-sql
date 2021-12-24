package io.simpolor.jpa.controller;

import io.simpolor.jpa.model.ClassroomDto;
import io.simpolor.jpa.service.ClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/classrooms")
@RestController
@RequiredArgsConstructor
public class ClassroomController {

	private final ClassroomService classroomService;

	@GetMapping(value="/total-count")
	public Long totalCount() {

		return classroomService.getTotalCount();
	}

	@GetMapping
	public List<ClassroomDto> list() {

		return ClassroomDto.of(classroomService.getAll());
	}

	@GetMapping(value="/{classroomId}")
	public ClassroomDto detail(@PathVariable Long classroomId) {

		return ClassroomDto.of(classroomService.get(classroomId));
	}

	@PostMapping
	public void register(@RequestBody ClassroomDto request) {

		classroomService.create(request.toEntity());
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
