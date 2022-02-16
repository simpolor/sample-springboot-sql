package io.simpolor.jpa.model;

import io.simpolor.jpa.repository.entity.Teacher;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDto {

    private Long id;
    private String name;
    private String subject;

    public Teacher toEntity(){

        return Teacher.builder()
                .teacherId(this.id)
                .name(this.name)
                .subject(this.subject)
                .build();
    }

    public static TeacherDto of(Teacher teacher){

        return TeacherDto.builder()
                .id(teacher.getTeacherId())
                .name(teacher.getName())
                .subject(teacher.getSubject())
                .build();
    }
}
