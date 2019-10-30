package io.simpolor.h2.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long seq;

	/* @Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
	)
	private long id; */

	private String name;
	private int grade;
	private int age;
}
