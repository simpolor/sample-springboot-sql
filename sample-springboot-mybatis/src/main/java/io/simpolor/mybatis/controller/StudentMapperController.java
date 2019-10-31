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

	@RequestMapping(value="/findByAllCount", method=RequestMethod.GET)
	public int studentFindByAllCount() {
		return studentMapperService.getStudentTotalCount();
	}

	@RequestMapping(value="/totalcount", method=RequestMethod.GET)
	public int studentTotalcount() {
		return studentMapperService.getStudentTotalCount();
	}

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public List<Student> studentList() {
		return studentMapperService.getStudentList();
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.GET)
	public Student studentView(@PathVariable long seq) {
		return studentMapperService.getStudent(seq);
	}

	@RequestMapping(value="", method=RequestMethod.POST)
	public Student studentRegister(@RequestBody Student student) {
		return studentMapperService.registerStudent(student);
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.PUT)
	public Student studentModify(@PathVariable long seq,
                                 @RequestBody Student student) {
		student.setSeq(seq);
		return studentMapperService.modifyStudent(student);
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.DELETE)
	public long studentDelete(@PathVariable long seq) {
		return studentMapperService.deleteStudent(seq);
	}


}
