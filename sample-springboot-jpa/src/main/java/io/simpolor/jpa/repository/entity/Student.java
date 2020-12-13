package io.simpolor.jpa.repository.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.simpolor.jpa.repository.convert.StringListConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

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

	/**
		@OneToOne
        - name :
        - cascade :
        	+ CascadeType.ALL :
        - fetch :
        	+ FetchType.LAZY :
			+ FetchType.EAGER :
		- orphanRemoval :
     */
	/**
		@JoinColumn
		- name :
		- insertable :
		- updatable :
	 */
	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "parent_seq")
	private Parent parent;

	/**
	 * 연관 관계가 주인이 아닌경우 @Transient를 통해 저장/불러오기시에 무시하도록 해야 에러가 나지 않음
	 */
	@Transient
	@OneToOne(mappedBy = "student")
	private Tag tag;

	/**
		@ElementCollection :
	 */
	/**
	 @CollectionTable :
	 	- name :
	 	- joinColumns :
	 */
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

	// @OneToMany(fetch = FetchType.LAZY)
	// @JoinColumn(name = "student_seq")
	// @OneToMany(mappedBy = "student")
	// private List<StudentClassroom> StudentClassrooms = new ArrayList<>();
}
