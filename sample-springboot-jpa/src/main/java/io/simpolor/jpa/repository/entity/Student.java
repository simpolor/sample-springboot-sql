package io.simpolor.jpa.repository.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.simpolor.jpa.repository.convert.StringListConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "student")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long seq;

	private String name;

	private int grade;

	private int age;

	@Convert(converter = StringListConverter.class)
	private List<String> hobbies;

	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL)
	// @OneToOne(mappedBy = "student")
	@JoinColumn(name = "parent_seq")
	private Parent parent;

	@ElementCollection
	@CollectionTable(
			name = "favorite_food",
			joinColumns = @JoinColumn(name = "student_seq")
	)
	@Column(name = "food_name") // 컬럼명 지정 (예외)
	private Set<String> foodNames = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "student_seq")
	private List<Teacher> teachers = new ArrayList<>();

	// @OneToMany(fetch = FetchType.LAZY, mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
	// @JoinColumn(name = "student_seq", insertable = false, updatable = false)
	// @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// @JoinColumn(name = "student_seq")
	// private Collection<Hobby> hobby;

	/*@OneToMany(mappedBy = "student")
	private List<StudentClassroom> StudentClassrooms = new ArrayList<>();*/
}
