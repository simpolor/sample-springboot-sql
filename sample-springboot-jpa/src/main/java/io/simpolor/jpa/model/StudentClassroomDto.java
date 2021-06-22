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
public class StudentClassroomDto {

    private long seq;
    private String name;

    public static StudentClassroomDto of(StudentClassroom studentClassroom){

        return StudentClassroomDto.builder()
                .seq(studentClassroom.getClassroom().getSeq())
                .name(studentClassroom.getClassroom().getClassName())
                .build();
    }

    public static List<StudentClassroomDto> of(List<StudentClassroom> studentClassrooms){

        return studentClassrooms.stream().map(StudentClassroomDto::of).collect(Collectors.toList());
    }

}
