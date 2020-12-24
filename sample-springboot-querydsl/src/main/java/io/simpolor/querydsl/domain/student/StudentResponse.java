package io.simpolor.querydsl.domain.student;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentResponse {

    private long seq;
    private String name;
    private int grade;
    private int age;

    public static StudentResponse of(Student student){

        return StudentResponse.builder()
                .seq(student.getSeq())
                .name(student.getName())
                .grade(student.getGrade())
                .age(student.getAge())
                .build();
    }

    public static List<StudentResponse> of(List<Student> students){

        return students.stream().map(s -> StudentResponse.of(s)).collect(Collectors.toList());
    }

}
