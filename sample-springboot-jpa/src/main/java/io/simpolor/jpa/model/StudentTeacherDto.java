package io.simpolor.jpa.model;

import io.simpolor.jpa.repository.entity.StudentTeacher;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentTeacherDto {

    private Long seq;
    private String name;

    public static StudentTeacherDto of(StudentTeacher studentTeacher){

        return StudentTeacherDto.builder()
                .seq(studentTeacher.getTeacher().getSeq())
                .name(studentTeacher.getTeacher().getTeacherName())
                .build();
    }

    public static List<StudentTeacherDto> of(List<StudentTeacher> studentTeachers){

        return studentTeachers.stream().map(StudentTeacherDto::of).collect(Collectors.toList());
    }

}
