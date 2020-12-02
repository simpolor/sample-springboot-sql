package io.simpolor.jpa.domain;

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
	private long seq;

	private String className;

	@OneToMany(mappedBy = "classroom")
	private List<StudentClassroom> students = new ArrayList<>();
}
