package io.simpolor.jpa.repository.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentTeacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long seq;

	@ManyToOne
	@JoinColumn(name = "student_seq")
	private Student student;

	@ManyToOne
	@JoinColumn(name = "teacher_seq")
	private Teacher teacher;
}
