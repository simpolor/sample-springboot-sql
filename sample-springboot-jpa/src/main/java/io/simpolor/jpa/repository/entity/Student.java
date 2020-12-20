package io.simpolor.jpa.repository.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.simpolor.jpa.repository.convert.StringListConverter;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity(name = "student")
@Table(name = "student")
@Getter
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
	/**
	 @JsonManagedReference
	 - 무한 루프를 방어하기 위한 JSON 애노테이션
	 - 불러오는 주체
	 - @JsonBackReference와 쌍으로 사용됨
	 */
	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "parent_seq")
	private Parent parent;

	/**
	 * 연관 관계가 주인이 아닌경우 @Transient를 통해 저장/불러오기시에 무시하도록 해야 에러가 나지 않음
	 * 주인이 아닌 경우 조회할때 연관 관계를 조회할 수 없음? ( 확인 필요 )
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
	@JoinColumn(name = "pet_seq")
	private List<Pet> pets = new ArrayList<>();

	@OneToMany(mappedBy = "student")
	private List<StudentClassroom> studentClassrooms = new ArrayList<>();

	@OneToMany(mappedBy = "student")
	private List<StudentTeacher> studentTeachers = new ArrayList<>();

	/***
	 * @Temporal
	 * - 데이터베이스에 저장될 타입을 지정
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date insert_date = new Date();

	/***
	 * @Enumerated(EnumType.STRING)
	 * - Enum 타입을 어떻게 저장할지 여부
	 */
}
