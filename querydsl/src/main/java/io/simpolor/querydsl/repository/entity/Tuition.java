package io.simpolor.querydsl.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tuition {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tuitionId;

	@OneToOne
	@JoinColumn(name = "student_id")
	private Student student;

	private Long fees;

}
