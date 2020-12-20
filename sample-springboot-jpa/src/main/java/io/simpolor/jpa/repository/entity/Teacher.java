package io.simpolor.jpa.repository.entity;

import lombok.*;

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
	private Long seq;

	private String teacherName;

	@OneToMany(mappedBy = "teacher")
	private List<StudentTeacher> studentTeachers = new ArrayList<>();
}
