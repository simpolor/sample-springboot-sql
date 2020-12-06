package io.simpolor.jpa.repository.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    private String fatherName;

    private String motherName;

    @JsonBackReference
    @OneToOne(mappedBy = "parent")
    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_seq")*/
    /*@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_seq")*/
    private Student student;

}
