package io.simpolor.jpa.controller;

import io.simpolor.jpa.model.TeacherDto;
import io.simpolor.jpa.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/teachers")
@RestController
@RequiredArgsConstructor
public class TeacherController {

	private final TeacherService teacherService;

	@GetMapping(value="/total-count")
	public Long totalCount() {

		return teacherService.getTotalCount();
	}

	@GetMapping
	public List<TeacherDto> list() {

		return TeacherDto.of(teacherService.getAll());
	}

	@GetMapping(value="/{seq}")
	public TeacherDto detail(@PathVariable Long seq) {

		return TeacherDto.of(teacherService.get(seq));
	}

	@PostMapping
	public void post(@RequestBody TeacherDto request) {

		teacherService.create(request.toEntity());
	}

	@PutMapping(value="/{seq}")
	public void put(@PathVariable Long seq,
					@RequestBody TeacherDto request) {

		request.setSeq(seq);
		teacherService.update(request.toEntity());
	}

	@DeleteMapping(value="/{seq}")
	public void delete(@PathVariable Long seq) {

		teacherService.delete(seq);
	}

}
