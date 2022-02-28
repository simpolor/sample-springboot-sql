package io.simpolor.mybatis.model;

import io.simpolor.mybatis.repository.entity.Student;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

	private Long id;
	private String name;
	private int grade;
	private int age;
	private List<String> hobbies;

	public Student toEntity(){
		Student student = new Student();
		student.setStudentId(this.id);
		student.setName(this.name);
		student.setGrade(this.grade);
		student.setAge(this.age);
		student.setHobbies(this.hobbies);

		return student;
	}

	public static StudentDto of(Student student){
		StudentDto studentDto = new StudentDto();
		studentDto.setId(student.getStudentId());
		studentDto.setName(student.getName());
		studentDto.setGrade(student.getGrade());
		studentDto.setAge(student.getAge());
		studentDto.setHobbies(student.getHobbies());

		return studentDto;
	}

	public static List<StudentDto> of(List<Student> students){

		return students.stream()
				.map(StudentDto::of)
				.collect(Collectors.toList());
	}
}
