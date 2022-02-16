package io.simpolor.jpa.repository.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.simpolor.jpa.repository.convert.StringListConverter;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ToString
@Entity(name = "student")
@Table(name = "student")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long studentId;

	private String name;
	private Integer grade;
	private Integer age;

	@Convert(converter = StringListConverter.class)
	private List<String> hobbies;

	/**
	 * @ElementCollection
	 * @CollectionTable :
	 *	- name :
	 *	- joinColumns :
	 */
	@ElementCollection
	@CollectionTable(
			name = "favorite_food",
			joinColumns = @JoinColumn(name = "student_id")
	)
	@Column(name = "food_name") // 컬럼명 지정 (예외)
	private Set<String> favoriteFoods = new HashSet<>();

	/**
	 * @OneToOne
	 * - name :
	 * - cascade :
	 * 	 + CascadeType.ALL :
	 * - fetch :
	 *   + FetchType.LAZY :
	 *   + FetchType.EAGER :
	 * @JoinColumn
	 * - name :
	 * - insertable :
	 * - updatable :
	 *
	 * 단방향에서 자식에서 조회시 부모를 함께 조회하기 위한 방법으로 cascade 옵션 추가
	 */
	/**
	 * @JsonManagedReference
	 * - 순환 참조로 인한 무한 루프를 방어하기 위한 JSON 애노테이션
	 * - @JsonManagedReference를 애노테이션을 선언 쪽이 주체
	 * - @JsonBackReference와 쌍으로 사용됨
	 */
	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "parent_id")
	private Parent parent;

	@ManyToOne
	@JoinColumn(name = "classroom_id")
	private Classroom classroom;

	/**
	 * mappedBy
	 * - 양방향 매핑일때 사용함
	 * - 테이블은 외래키를 하나만으로 연관 관계를 맺음으로써, 어떤 테이블에서 외래키를 관리할지 결정
	 * - 연관 관계의 주인은 mappedBy를 사용하지 않음
	 * - mappedBy 속성이 있을 경우 조회만 가능
	 *
	 * CascadeType.Remove
	 * - 참조를 변경시켜 무결성 오류를 안나게 동작
	 * - 관계가 끊어졌다고 데이터를 삭제하지는 않음
	 *
	 * orphanRemoval=true
	 * - 관계가 끊어졌을 경우 자식 객체의 데이터를 삭제
	 */
	// @OneToMany(mappedBy = "student")
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name = "student_id")
	private List<StudentTeacher> studentTeachers = new ArrayList<>();

	public Student(Long studentId){
		this.studentId = studentId;
	}

	/***
	 * @Temporal
	 * - 데이터베이스에 저장될 타입을 지정
	 */
	// @Temporal(TemporalType.TIMESTAMP)
	// private Date insertDate = new Date();

	/***
	 * @Enumerated(EnumType.STRING)
	 * - Enum 타입을 어떻게 저장할지 여부
	 */

	/**
	 * @Transient를 통해 저장/불러오기시에 무시하도록 설정
	 */

}
