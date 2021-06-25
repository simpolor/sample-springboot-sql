package io.simpolor.querydsl.controller;

import io.simpolor.querydsl.model.StudentTuitionDto;
import io.simpolor.querydsl.model.TuitionDto;
import io.simpolor.querydsl.service.TuitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tuitions")
@RequiredArgsConstructor
public class TuitionController {

	private final TuitionService expenseService;

	@GetMapping
	public List<TuitionDto> list() {
		return TuitionDto.of(expenseService.getAll());
	}

	@GetMapping("/order-desc")
	public List<TuitionDto> listByOrderDesc() {
		return TuitionDto.of(expenseService.getAllByOrderSeqDesc());
	}

	@GetMapping("/student-group")
	public List<StudentTuitionDto> listByStudentGroup() {
		return StudentTuitionDto.of(expenseService.getAllByStudentGroup());
	}

}

