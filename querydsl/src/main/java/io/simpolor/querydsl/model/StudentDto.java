package io.simpolor.querydsl.model;

import io.simpolor.querydsl.repository.entity.Student;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    private Long id;
    private String name;
    private Integer grade;
    private Integer age;

    public Student toEntity(){

        return Student.builder()
                .studentId(this.id)
                .name(this.name)
                .grade(this.grade)
                .age(this.age)
                .build();
    }

    public static StudentDto of(Student student){

        return StudentDto.builder()
                .id(student.getStudentId())
                .name(student.getName())
                .grade(student.getGrade())
                .age(student.getAge())
                .build();
    }

    public static List<StudentDto> of(List<Student> students){

        return students.stream()
                .map(StudentDto::of)
                .collect(Collectors.toList());
    }

    @Setter
    @Getter
    public static class StudentSearch {

        private String name;

        public Student toEntity(){

            return Student.builder()
                    .name(this.name)
                    .build();
        }
    }
}
