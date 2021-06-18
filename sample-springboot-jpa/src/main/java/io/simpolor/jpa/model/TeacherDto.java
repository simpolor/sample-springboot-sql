package io.simpolor.jpa.model;

import io.simpolor.jpa.repository.entity.StudentTeacher;
import io.simpolor.jpa.repository.entity.Teacher;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDto {

    private Long seq;
    private String name;

    public Teacher toEntity(){

        return Teacher.builder()
                .teacherName(this.name)
                .build();
    }

    public static TeacherDto of(Teacher teacher){

        return TeacherDto.builder()
                .seq(teacher.getSeq())
                .name(teacher.getTeacherName())
                .build();
    }

    public static List<TeacherDto> of(List<Teacher> teachers){

        return teachers.stream().map(TeacherDto::of).collect(Collectors.toList());
    }

    public static TeacherDto ofStudent(StudentTeacher studentTeacher){

        return TeacherDto.builder()
                .seq(studentTeacher.getTeacher().getSeq())
                .name(studentTeacher.getTeacher().getTeacherName())
                .build();
    }

    public static List<TeacherDto> ofStudent(List<StudentTeacher> studentTeachers){

        return studentTeachers.stream().map(TeacherDto::ofStudent).collect(Collectors.toList());
    }

}
