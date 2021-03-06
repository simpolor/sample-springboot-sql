package io.simpolor.jpa.repository.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentClassroom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long seq;

	/***
	 * @ManyToOne은 항상 주인이 되므로 mappedBy를 설정할 수 없음
	 */
	@ManyToOne
	@JoinColumn(name = "student_seq")
	private Student student;

	@ManyToOne
	@JoinColumn(name = "classroom_seq")
	private Classroom classroom;

}
