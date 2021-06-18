package io.simpolor.jpa.model;

import io.simpolor.jpa.repository.entity.StudentClassroom;
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
public class ClassroomDto {

    private long seq;
    private String name;

    public static ClassroomDto of(StudentClassroom studentClassroom){

        return ClassroomDto.builder()
                .seq(studentClassroom.getClassroom().getSeq())
                .name(studentClassroom.getClassroom().getClassName())
                .build();
    }

    public static List<ClassroomDto> of(List<StudentClassroom> studentClassrooms){

        return studentClassrooms.stream().map(ClassroomDto::of).collect(Collectors.toList());
    }

}
