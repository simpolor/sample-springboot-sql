package io.simpolor.jpa.domain;

import io.simpolor.jpa.repository.convert.StringListConverter;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long seq;

	@Column(name = "name", nullable = false)
	private String name;

	private int grade;

	private int age;

	@Convert(converter = StringListConverter.class)
	private List<String> hobby;

}
