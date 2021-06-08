package io.simpolor.h2.model;

import io.simpolor.h2.repository.entity.Student;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class StudentResponse {

	private long seq;
	private String name;
	private int grade;
	private int age;

	public static StudentResponse of(Student student){

		StudentResponse studentResponse = new StudentResponse();
		studentResponse.setSeq(student.getSeq());
		studentResponse.setName(student.getName());
		studentResponse.setGrade(student.getGrade());
		studentResponse.setAge(student.getAge());

		return studentResponse;
	}

	public static List<StudentResponse> of(List<Student> students){

		return students.stream()
				.map(StudentResponse::of)
				.collect(Collectors.toList());
	}
}
