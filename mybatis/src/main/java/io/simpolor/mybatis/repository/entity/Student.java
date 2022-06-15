package io.simpolor.mybatis.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	private Long studentId;
	private String name;
	private Integer grade;
	private Integer age;
	private List<String> hobbies;
}
