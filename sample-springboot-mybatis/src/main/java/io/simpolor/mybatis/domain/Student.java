package io.simpolor.mybatis.domain;

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

	private long seq;
	private String name;
	private int grade;
	private int age;
	private List<String> hobby;
}
