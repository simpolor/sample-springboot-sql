package io.simpolor.mysql.domain;

import io.simpolor.mysql.convert.StringListConverter;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
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
