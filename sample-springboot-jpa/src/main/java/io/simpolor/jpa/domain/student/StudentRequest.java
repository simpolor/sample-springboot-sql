package io.simpolor.jpa.domain.student;

import io.simpolor.jpa.repository.entity.*;
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
public class StudentRequest {

    private long seq;
    private String name;
    private int grade;
    private int age;

    private List<String> hobbies = new ArrayList<>();
    private Set<String> favoriteFoods = new HashSet<>();
    private List<String> teachers = new ArrayList<>();
    private Parent parent;
    private Tag tag;
    private List<Long> classroomSequences = new ArrayList<>();

    public Student toStudent(){

        Student student = new Student();
        student.setName(this.name);
        student.setGrade(this.grade);
        student.setAge(this.age);
        student.setHobbies(this.hobbies);
        student.setFoodNames(this.favoriteFoods);
        student.setParent(this.parent);
        student.setTag(this.tag);
        student.setTeachers(Teacher.of(this.teachers));
        student.setStudentClassrooms(
                this.classroomSequences.stream().map(o -> new StudentClassroom(o)).collect(Collectors.toList())
        );

        return student;
    }

    public Student toStudent(long seq){

        Student student = new Student();
        student.setSeq(seq);
        student.setName(name);
        student.setGrade(grade);
        student.setAge(age);
        student.setHobbies(this.hobbies);
        student.setFoodNames(this.favoriteFoods);
        student.setParent(this.parent);
        student.setTag(this.tag);
        student.setTeachers(Teacher.of(this.teachers));

        return student;
    }
}
