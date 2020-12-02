package io.simpolor.jpa.controller;

import io.simpolor.jpa.repository.entity.Student;
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
	public List<Student> list() {

		return studentService.getAll();
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.GET)
	public Student view(@PathVariable long seq) {

		return studentService.get(seq);
	}

	@RequestMapping(value="", method=RequestMethod.POST)
	public void register(@RequestBody Student student) {

		studentService.register(student);
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.PUT)
	public void modify(@PathVariable int seq,
					   @RequestBody Student student) {

		student.setSeq(seq);
		studentService.modify(student);
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
