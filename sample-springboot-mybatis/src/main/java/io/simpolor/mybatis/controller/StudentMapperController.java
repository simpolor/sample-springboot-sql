package io.simpolor.mybatis.controller;

import io.simpolor.mybatis.domain.Student;
import io.simpolor.mybatis.service.StudentMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/mapper/student")
@RestController
public class StudentMapperController {

	@Autowired
	private StudentMapperService studentMapperService;

	@RequestMapping(value="/totalCount", method=RequestMethod.GET)
	public long getTotalCount() {
		return studentMapperService.getTotalCount();
	}

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public List<Student> getList() {
		return studentMapperService.getAll();
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.GET)
	public Student get(@PathVariable long seq) {
		return studentMapperService.get(seq);
	}

	@RequestMapping(value="", method=RequestMethod.POST)
	public void post(@RequestBody Student student) {
		studentMapperService.register(student);
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.PUT)
	public void put(@PathVariable long seq, @RequestBody Student student) {
		student.setSeq(seq);
		studentMapperService.modify(student);
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.DELETE)
	public void delete(@PathVariable long seq) {
		studentMapperService.delete(seq);
	}


}
