package io.simpolor.jpa.repository.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.simpolor.jpa.repository.convert.StringListConverter;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	 @OneToOne
	 - name :
	 - cascade :
	 + CascadeType.ALL :
	 - fetch :
	 + FetchType.LAZY :
	 + FetchType.EAGER :
	 - orphanRemoval :

	 @JoinColumn
	 - name :
	 - insertable :
	 - updatable :

	 단방향에서 자식에서 조회시 부모를 함께 조회하기 위한 방법으로 cascade 옵션 추가
 	*/
	/**
	 @JsonManagedReference
	 - 순환 참조로 인한 무한 루프를 방어하기 위한 JSON 애노테이션
	 - @JsonManagedReference를 애노테이션을 선언 쪽이 주체
	 - @JsonBackReference와 쌍으로 사용됨
	 */
	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "parent_id")
	private Parent parent;

	@ManyToOne
	@JoinColumn(name = "classroom_id")
	private Classroom classroom;

	public Student(Long studentId){
		this.studentId = studentId;
	}

}
