package io.simpolor.jpa.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Classroom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;

	private String className;

	@OneToMany
	private List<StudentClassroom> students = new ArrayList<>();

	public Classroom(Long seq){
		this.seq = seq;
	}
}
