package io.simpolor.jpa.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long teacherId;

	private String teacherName;

	@OneToMany(mappedBy = "teacher")
	private List<StudentTeacher> studentTeachers = new ArrayList<>();

	public Teacher(Long teacherId){
		this.teacherId = teacherId;
	}
}
