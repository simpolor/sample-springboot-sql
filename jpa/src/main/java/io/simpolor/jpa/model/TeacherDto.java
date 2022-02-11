package io.simpolor.jpa.model;

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

    private Long id;
    private String name;

    public Teacher toEntity(){

        return Teacher.builder()
                .teacherName(this.name)
                .build();
    }

    public static TeacherDto of(Teacher teacher){

        return TeacherDto.builder()
                .id(teacher.getTeacherId())
                .name(teacher.getTeacherName())
                .build();
    }
}
