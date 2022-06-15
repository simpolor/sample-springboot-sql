package io.simpolor.jdbc.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	private Long studentId;
	private String name;
	private Integer grade;
	private Integer age;
	private List<String> hobbies;

}
