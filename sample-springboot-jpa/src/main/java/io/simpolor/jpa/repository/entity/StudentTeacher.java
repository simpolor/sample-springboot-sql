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
	private long seq;

	@ManyToOne
	@JoinColumn(name = "student_seq")
	private Student student;

	@ManyToOne
	@JoinColumn(name = "teacher_seq")
	private Teacher teacher;

	public static StudentTeacher of(Long studentSeq, Long teacherSeq){

		Student student = Student.builder().seq(studentSeq).build();
		Teacher teacher = Teacher.builder().seq(teacherSeq).build();

		return StudentTeacher.builder()
				.student(student)
				.teacher(teacher)
				.build();
	}
}
