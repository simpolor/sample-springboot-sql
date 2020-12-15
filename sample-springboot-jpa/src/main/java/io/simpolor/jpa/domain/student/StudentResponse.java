package io.simpolor.jpa.domain.student;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.simpolor.jpa.repository.entity.Parent;
import io.simpolor.jpa.repository.entity.Student;
import io.simpolor.jpa.repository.entity.Tag;
import io.simpolor.jpa.repository.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    private List<Teacher> teachers;
    private Parent parent;

    public static StudentResponse of(Student student){

        return StudentResponse.builder()
                .seq(student.getSeq())
                .name(student.getName())
                .grade(student.getGrade())
                .age(student.getAge())
                .favoriteFoods(student.getFoodNames())
                .hobbies(student.getHobbies())
                .teachers(student.getTeachers())
                .parent(student.getParent())
                .build();
    }

    public static List<StudentResponse> of(List<Student> students){

        return students.stream().map(s -> StudentResponse.of(s)).collect(Collectors.toList());
    }

}
