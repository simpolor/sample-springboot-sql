package io.simpolor.mysql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "student_classroom")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentClassroom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long seq;

	private int ordering;

	// @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@ManyToOne
	@JoinColumn(name = "student_seq")
	private Student student;

	// @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@ManyToOne
	@JoinColumn(name = "classroom_seq")
	private Classroom classroom;
}
