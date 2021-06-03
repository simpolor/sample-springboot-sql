package io.simpolor.jpa.controller;

import io.simpolor.jpa.model.teacher.TeacherRequest;
import io.simpolor.jpa.model.teacher.TeacherResponse;
import io.simpolor.jpa.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/teacher")
@RestController
public class TeacherController {

	@Autowired
	private TeacherService teacherService;

	@RequestMapping(value="/totalCount", method=RequestMethod.GET)
	public long totalCount() {

		return teacherService.getTotalCount();
	}

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public List<TeacherResponse> getList() {

		return TeacherResponse.of(teacherService.getAll());
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.GET)
	public TeacherResponse get(@PathVariable long seq) {

		return TeacherResponse.of(teacherService.get(seq));
	}

	@RequestMapping(value="", method=RequestMethod.POST)
	public void post(@RequestBody TeacherRequest request) {

		teacherService.register(request.toTeacher());
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.PUT)
	public void put(@PathVariable int seq,
					@RequestBody TeacherRequest request) {

		teacherService.modify(request.toTeacher(seq));
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.DELETE)
	public void delete(@PathVariable long seq) {

		teacherService.delete(seq);
	}

}
