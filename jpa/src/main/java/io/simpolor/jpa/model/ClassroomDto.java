package io.simpolor.jpa.model;

import io.simpolor.jpa.repository.entity.Classroom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomDto {

    private long id;
    private String name;
    private List<StudentDto> students;

    public Classroom toEntity() {

        return Classroom.builder()
                .classroomId(this.id)
                .name(this.name)
                .build();
    }

    public static ClassroomDto of(Classroom classroom){

        return ClassroomDto.builder()
                .id(classroom.getClassroomId())
                .name(classroom.getName())
                .students(
                        !CollectionUtils.isEmpty(classroom.getStudents())
                                ? classroom.getStudents().stream().map(StudentDto::of).collect(Collectors.toList())
                                : null)
                .build();
    }

    public static List<ClassroomDto> of(List<Classroom> parents){

        return parents.stream()
                .map(ClassroomDto::of)
                .collect(Collectors.toList());
    }

}
