package io.simpolor.jpa.domain.student;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.simpolor.jpa.domain.classroom.ClassroomResponse;
import io.simpolor.jpa.domain.parent.ParentResponse;
import io.simpolor.jpa.domain.pet.PetResponse;
import io.simpolor.jpa.domain.tag.TagResponse;
import io.simpolor.jpa.domain.teacher.TeacherResponse;
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
public class StudentResponse {

    private long seq;
    private String name;
    private int grade;
    private int age;

    private Set<String> favoriteFoods = new HashSet<>();
    private List<String> hobbies = new ArrayList<>();
    private List<PetResponse> pets;
    private ParentResponse parent;
    private TagResponse tag;
    private List<ClassroomResponse> classrooms;
    private List<TeacherResponse> teachers;

    public static StudentResponse of(Student student){

        return StudentResponse.builder()
                .seq(student.getSeq())
                .name(student.getName())
                .grade(student.getGrade())
                .age(student.getAge())
                .favoriteFoods(student.getFoodNames())
                .hobbies(student.getHobbies())
                .pets((Objects.nonNull(student.getPets())) ? PetResponse.of(student.getPets()) : null)
                .tag((Objects.nonNull(student.getTag())) ? TagResponse.of(student.getTag()) : null)
                .parent((Objects.nonNull(student.getParent())) ? ParentResponse.of(student.getParent()) : null)
                .classrooms(ClassroomResponse.of(student.getStudentClassrooms()))
                .teachers(TeacherResponse.ofStudent(student.getStudentTeachers()))
                .build();
    }

    public static List<StudentResponse> of(List<Student> students){

        return students.stream().map(s -> StudentResponse.of(s)).collect(Collectors.toList());
    }

}
