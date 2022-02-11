package io.simpolor.jpa.repository.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentTeacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long studentTeacherId;

	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;

	public StudentTeacher(Long studentId, Long teacherId){
		this.student = new Student(studentId);
		this.teacher = new Teacher(teacherId);
	}
}
