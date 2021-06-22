package io.simpolor.jpa.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.simpolor.jpa.repository.entity.Student;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDto {

    private Long seq;
    private String name;
    private Integer grade;
    private Integer age;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Set<String> favoriteFoods = new HashSet<>();
    private List<String> hobbies = new ArrayList<>();
    private List<PetDto> pets;
    private ParentDto parent;
    private TagDto tag;
    private List<String> classroomNames;
    private List<StudentClassroomDto> classrooms;
    private List<Long> teacherSeqs;
    private List<StudentTeacherDto> teachers;

    public Student toEntity(){

        return Student.builder()
                .seq(this.seq)
                .name(this.name)
                .grade(this.grade)
                .age(this.age)
                .hobbies(this.hobbies)
                .foodNames(this.favoriteFoods)
                .parent((Objects.nonNull(this.parent)) ? this.parent.toEntity() : null)
                .tag((Objects.nonNull(this.tag)) ? this.tag.toEntity() : null)
                .pets(this.pets.stream().map(PetDto::toEntity).collect(Collectors.toList()))
                .build();
    }

    public static StudentDto of(Student student){

        return StudentDto.builder()
                .seq(student.getSeq())
                .name(student.getName())
                .grade(student.getGrade())
                .age(student.getAge())
                .createdAt(student.getCreatedAt())
                .updatedAt(student.getUpdatedAt())
                .favoriteFoods(student.getFoodNames())
                .hobbies(student.getHobbies())
                .pets((Objects.nonNull(student.getPets())) ? PetDto.of(student.getPets()) : null)
                .tag((Objects.nonNull(student.getTag())) ? TagDto.of(student.getTag()) : null)
                .parent((Objects.nonNull(student.getParent())) ? ParentDto.of(student.getParent()) : null)
                .classrooms(StudentClassroomDto.of(student.getStudentClassrooms()))
                .teachers(StudentTeacherDto.of(student.getStudentTeachers()))
                .build();
    }

    public static List<StudentDto> of(List<Student> students){

        return students.stream().map(s -> StudentDto.of(s)).collect(Collectors.toList());
    }

}
