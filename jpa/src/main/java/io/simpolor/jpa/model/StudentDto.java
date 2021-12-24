package io.simpolor.jpa.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.simpolor.jpa.repository.entity.Classroom;
import io.simpolor.jpa.repository.entity.Student;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDto {

    private Long id;
    private String name;
    private Integer grade;
    private Integer age;

    private Long classroomId;
    private ClassroomDto classroomDto;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Student toEntity(){

        return Student.builder()
                .studentId(this.id)
                .name(this.name)
                .grade(this.grade)
                .age(this.age)
                .classroom(Classroom.builder().classroomId(this.classroomId).build())
                .build();
    }

    public static StudentDto of(Student student){

        return StudentDto.builder()
                .id(student.getStudentId())
                .name(student.getName())
                .grade(student.getGrade())
                .age(student.getAge())
                .classroomDto(
                        Objects.nonNull(student.getClassroom())
                                ? ClassroomDto.of(student.getClassroom())
                                : null)
                .createdAt(student.getCreatedAt())
                .updatedAt(student.getUpdatedAt())
                .build();
    }

    public static List<StudentDto> of(List<Student> students){

        return students.stream()
                .map(StudentDto::of)
                .collect(Collectors.toList());
    }

}
