package io.simpolor.jpa.controller;

import io.simpolor.jpa.domain.student.StudentRequest;
import io.simpolor.jpa.domain.student.StudentResponse;
import io.simpolor.jpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/student")
@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;

	@RequestMapping(value="/totalCount", method=RequestMethod.GET)
	public long totalCount() {

		return studentService.getTotalCount();
	}

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public List<StudentResponse> getList() {

		return StudentResponse.of(studentService.getAll());
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.GET)
	public StudentResponse get(@PathVariable long seq) {

		return StudentResponse.of(studentService.get(seq));
	}

	@RequestMapping(value="", method=RequestMethod.POST)
	public void post(@RequestBody StudentRequest request) {

		studentService.register(request.toStudent(), request.getClassroomNames(), request.getTeacherSequences());
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.PUT)
	public void put(@PathVariable int seq,
					@RequestBody StudentRequest request) {

		studentService.modify(request.toStudent(seq), request.getClassroomNames(), request.getTeacherSequences());
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.DELETE)
	public void delete(@PathVariable long seq) {

		studentService.delete(seq);
	}

	@RequestMapping(value="/not", method=RequestMethod.GET)
	public String studentNot() {

		return "Is not a studnet";
	}


}
