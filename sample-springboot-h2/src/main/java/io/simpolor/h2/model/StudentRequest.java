package io.simpolor.h2.model;

import io.simpolor.h2.repository.entity.Student;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentRequest {

	private long seq;
	private String name;
	private int grade;
	private int age;

	public Student toEntity(){

		Student student = new Student();
		student.setSeq(this.seq);
		student.setName(this.name);
		student.setGrade(this.grade);
		student.setAge(this.age);

		return student;
	}
}
