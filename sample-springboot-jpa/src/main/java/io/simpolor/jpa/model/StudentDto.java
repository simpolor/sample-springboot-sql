package io.simpolor.jpa.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.simpolor.jpa.repository.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDto {

    private long seq;
    private String name;
    private int grade;
    private int age;

    private Set<String> favoriteFoods = new HashSet<>();
    private List<String> hobbies = new ArrayList<>();
    private List<PetDto> pets;
    private ParentDto parent;
    private TagDto tag;
    private List<ClassroomDto> classrooms;
    private List<TeacherDto> teachers;

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
                .favoriteFoods(student.getFoodNames())
                .hobbies(student.getHobbies())
                .pets((Objects.nonNull(student.getPets())) ? PetDto.of(student.getPets()) : null)
                .tag((Objects.nonNull(student.getTag())) ? TagDto.of(student.getTag()) : null)
                .parent((Objects.nonNull(student.getParent())) ? ParentDto.of(student.getParent()) : null)
                .classrooms(ClassroomDto.of(student.getStudentClassrooms()))
                .teachers(TeacherDto.ofStudent(student.getStudentTeachers()))
                .build();
    }

    public static List<StudentDto> of(List<Student> students){

        return students.stream().map(s -> StudentDto.of(s)).collect(Collectors.toList());
    }

}
