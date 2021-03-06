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
public class Classroom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;

	private String className;

	@OneToMany(mappedBy = "classroom")
	private List<StudentClassroom> studentClassrooms = new ArrayList<>();
}
