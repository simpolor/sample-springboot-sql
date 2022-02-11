package io.simpolor.jpa.repository.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parentId;

    private String fatherName;

    private String motherName;

    /*
        mappedBy : 1. 연관관계 주인 설정 ( 양방향 설정시 하나는 꼭 주인 설정이 필요 )
                   2. 주인은 CRUD 가능, 아닌 경우 읽기만 가능
                   3. 주인이 아닌 경우
     */
    @JsonBackReference
    @OneToOne(mappedBy = "parent")
    private Student student;

}
