package io.simpolor.mybatis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

	private long seq;
	private String name;
	private int grade;
	private int age;
	private List<String> hobby;
}
