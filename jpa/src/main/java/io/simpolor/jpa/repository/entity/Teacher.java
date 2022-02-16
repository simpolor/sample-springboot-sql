package io.simpolor.jpa.repository.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString
@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long teacherId;

	private String name;
	private String subject;

	// @OneToMany(mappedBy = "teacher")
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name = "teacher_id")
	private List<StudentTeacher> studentTeachers = new ArrayList<>();

	public Teacher(Long teacherId){
		this.teacherId = teacherId;
	}
}
