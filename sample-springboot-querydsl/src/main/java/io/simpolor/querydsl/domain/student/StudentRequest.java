package io.simpolor.querydsl.domain.student;

import io.simpolor.querydsl.repository.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {

    private long seq;
    private String name;
    private int grade;
    private int age;

    public Student toStudent(){

        return Student.builder()
                .name(this.name)
                .grade(this.grade)
                .age(this.age)
                .build();
    }

    public Student toStudent(long seq){

        return Student.builder()
                .seq(seq)
                .name(this.name)
                .grade(this.grade)
                .age(this.age)
                .build();
    }
}
