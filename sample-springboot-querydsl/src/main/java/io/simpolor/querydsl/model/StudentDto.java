package io.simpolor.querydsl.model;

import io.simpolor.querydsl.repository.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    private Long seq;
    private String name;
    private Integer grade;
    private Integer age;

    public Student toEntity(){

        return Student.builder()
                .seq(this.seq)
                .name(this.name)
                .grade(this.grade)
                .age(this.age)
                .build();
    }

    public static StudentDto of(Student student){

        return StudentDto.builder()
                .seq(student.getSeq())
                .name(student.getName())
                .grade(student.getGrade())
                .age(student.getAge())
                .build();
    }

    public static List<StudentDto> of(List<Student> students){

        return students.stream()
                .map(StudentDto::of)
                .collect(Collectors.toList());
    }

}
