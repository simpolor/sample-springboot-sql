package io.simpolor.jpa.domain.teacher;

import io.simpolor.jpa.repository.entity.StudentTeacher;
import io.simpolor.jpa.repository.entity.Teacher;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherResponse {

    private long seq;
    private String name;

    public static TeacherResponse of(Teacher teacher){

        return TeacherResponse.builder()
                .seq(teacher.getSeq())
                .name(teacher.getTeacherName())
                .build();
    }

    public static List<TeacherResponse> of(List<Teacher> teachers){

        return teachers.stream().map(TeacherResponse::of).collect(Collectors.toList());
    }

    public static TeacherResponse ofStudent(StudentTeacher studentTeacher){

        return TeacherResponse.builder()
                .seq(studentTeacher.getTeacher().getSeq())
                .name(studentTeacher.getTeacher().getTeacherName())
                .build();
    }

    public static List<TeacherResponse> ofStudent(List<StudentTeacher> studentTeachers){

        return studentTeachers.stream().map(TeacherResponse::ofStudent).collect(Collectors.toList());
    }

}
