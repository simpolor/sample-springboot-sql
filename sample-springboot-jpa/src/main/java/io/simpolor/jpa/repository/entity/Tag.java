package io.simpolor.jpa.repository.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    private String title;

    @OneToOne
    @JoinColumn(name = "student_seq")
    private Student student;
}
