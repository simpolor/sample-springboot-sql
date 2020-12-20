package io.simpolor.jpa.domain.classroom;

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
public class ClassroomResponse {

    private long seq;
    private String name;

    public static ClassroomResponse of(StudentClassroom studentClassroom){

        return ClassroomResponse.builder()
                .seq(studentClassroom.getClassroom().getSeq())
                .name(studentClassroom.getClassroom().getClassName())
                .build();
    }

    public static List<ClassroomResponse> of(List<StudentClassroom> studentClassrooms){

        return studentClassrooms.stream().map(ClassroomResponse::of).collect(Collectors.toList());
    }

}
