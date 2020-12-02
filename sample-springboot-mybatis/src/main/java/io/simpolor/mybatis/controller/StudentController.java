package io.simpolor.mybatis.controller;

import io.simpolor.mybatis.domain.Student;
import io.simpolor.mybatis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/student")
@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;

	@RequestMapping(value="/totalCount", method=RequestMethod.GET)
	public long getTotalCount() {
		return studentService.getTotalCount();
	}

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public List<Student> getList() {
		return studentService.getAll();
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.GET)
	public Student get(@PathVariable long seq) {
		return studentService.get(seq);
	}

	@RequestMapping(value="", method=RequestMethod.POST)
	public void post(@RequestBody Student student) {
		studentService.register(student);
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.PUT)
	public void put(@PathVariable int seq,
								 @RequestBody Student student) {
		student.setSeq(seq);
		studentService.modify(student);
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.DELETE)
	public void delete(@PathVariable long seq) {
		studentService.delete(seq);

	}


}
