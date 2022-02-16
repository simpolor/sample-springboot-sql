package io.simpolor.jpa.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.simpolor.jpa.repository.entity.*;
import lombok.*;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
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
    private List<String> hobbies;
    private Set<String> favoriteFoods;
    private Long classroomId;
    private List<Long> teacherIds;
    private ParentDto parent;

    public static StudentDto of(Student student){

        return StudentDto.builder()
                .id(student.getStudentId())
                .name(student.getName())
                .grade(student.getGrade())
                .age(student.getAge())
                .hobbies(student.getHobbies())
                .favoriteFoods(student.getFavoriteFoods())
                .parent(
                    Objects.nonNull(student.getParent())
                            ? ParentDto.of(student.getParent())
                            : null
                )
                .classroomId(
                        Objects.nonNull(student.getClassroom())
                                ? student.getClassroom().getClassroomId()
                                : null
                )
                .teacherIds(
                        !CollectionUtils.isEmpty(student.getStudentTeachers())
                            ? student.getStudentTeachers().stream()
                                .map(StudentTeacher::getTeacher)
                                .map(Teacher::getTeacherId)
                                .collect(Collectors.toList())
                            : null
                )
                .build();
    }

    public Student toEntity(){

        return Student.builder()
                .studentId(this.id)
                .name(this.name)
                .grade(this.grade)
                .age(this.age)
                .hobbies(this.hobbies)
                .favoriteFoods(this.favoriteFoods)
                .classroom(Classroom.builder().classroomId(this.classroomId).build())
                .studentTeachers(
                        !CollectionUtils.isEmpty(this.teacherIds)
                                ? teacherIds.stream()
                                    .map(id -> new StudentTeacher(id))
                                    .collect(Collectors.toList())
                                : null
                )
                .parent(Objects.nonNull(parent) ? parent.toEntity() : null)
                .build();
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class StudentDetail {

        private Long id;
        private String name;
        private Integer grade;
        private Integer age;
        private List<String> hobbies;
        private Set<String> favoriteFoods;
        private ClassroomDto.ClassroomDetail classroom;
        private ParentDto.ParentDetail parent;
        private List<TeacherDto> teachers;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public static StudentDetail of(Student student){

            return StudentDetail.builder()
                    .id(student.getStudentId())
                    .name(student.getName())
                    .grade(student.getGrade())
                    .age(student.getAge())
                    .hobbies(student.getHobbies())
                    .favoriteFoods(student.getFavoriteFoods())
                    .classroom(
                            Objects.nonNull(student.getClassroom())
                                    ? ClassroomDto.ClassroomDetail.ofStudent(student.getClassroom())
                                    : null)
                    .parent(
                            Objects.nonNull(student.getParent())
                                    ? ParentDto.ParentDetail.of(student.getParent())
                                    : null)
                    .teachers(
                            !CollectionUtils.isEmpty(student.getStudentTeachers())
                                    ? student.getStudentTeachers().stream()
                                    .map(StudentTeacher::getTeacher)
                                    .map(TeacherDto::of)
                                    .collect(Collectors.toList())
                                    : null
                    )
                    .createdAt(student.getCreatedAt())
                    .updatedAt(student.getUpdatedAt())
                    .build();
        }

        public static StudentDetail ofClassroom(Student student){

            return StudentDetail.builder()
                    .id(student.getStudentId())
                    .name(student.getName())
                    .grade(student.getGrade())
                    .age(student.getAge())
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ParentDto {

        private Long id;
        private String fatherName;
        private String motherName;

        public Parent toEntity(){

            return Parent.builder()
                    .parentId(this.id)
                    .fatherName(this.fatherName)
                    .motherName(this.motherName)
                    .build();
        }

        public static ParentDto of(Parent parent) {

            return ParentDto.builder()
                    .id(parent.getParentId())
                    .fatherName(parent.getFatherName())
                    .motherName(parent.getMotherName())
                    .build();
        }

        @Getter
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class ParentDetail {

            private Long id;
            private String fatherName;
            private String motherName;

            public static ParentDto.ParentDetail of(Parent parent) {

                return ParentDetail.builder()
                        .id(parent.getParentId())
                        .fatherName(parent.getFatherName())
                        .motherName(parent.getMotherName())
                        .build();
            }
        }
    }

}
