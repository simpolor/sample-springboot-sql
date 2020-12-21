package io.simpolor.jpa.domain.student;

import io.simpolor.jpa.domain.pet.PetRequest;
import io.simpolor.jpa.domain.tag.TagRequest;
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
    private List<PetRequest> pets = new ArrayList<>();
    private Parent parent;
    private TagRequest tag;
    private List<String> classroomNames = new ArrayList<>();
    private List<Long> teacherSequences = new ArrayList<>();

    public Student toStudent(){

        return Student.builder()
                .name(this.name)
                .grade(this.grade)
                .age(this.age)
                .hobbies(this.hobbies)
                .foodNames(this.favoriteFoods)
                .parent(this.parent)
                .tag(this.tag.toInsert())
                .pets(this.pets.stream().map(PetRequest::toPet).collect(Collectors.toList()))
                .build();
    }

    public Student toStudent(long seq){

        return Student.builder()
                .seq(seq)
                .name(this.name)
                .grade(this.grade)
                .age(this.age)
                .hobbies(this.hobbies)
                .foodNames(this.favoriteFoods)
                .parent(this.parent)
                .tag(this.tag.toUpdate())
                .pets(this.pets.stream().map(PetRequest::toPet).collect(Collectors.toList()))
                .build();
    }
}
