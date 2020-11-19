package io.simpolor.h2.controller;

import io.simpolor.h2.domain.Student;
import io.simpolor.h2.service.StudentService;
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
	public List<Student> list() {

		return studentService.getAll();
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.GET)
	public Student view(@PathVariable long seq) {

		return studentService.get(seq);
	}

	@RequestMapping(value="", method=RequestMethod.POST)
	public Student register(@RequestBody Student student) {

		return studentService.register(student);
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.PUT)
	public Student modify(@PathVariable int seq,
						  @RequestBody Student student) {

		student.setSeq(seq);
		return studentService.modify(student);
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.DELETE)
	public long delete(@PathVariable long seq) {

		return studentService.delete(seq);
	}

	@RequestMapping(value="/not", method=RequestMethod.GET)
	public String studentNot() {

		return "Is not a studnet";
	}


}
