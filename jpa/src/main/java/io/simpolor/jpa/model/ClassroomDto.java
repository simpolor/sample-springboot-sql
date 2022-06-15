package io.simpolor.jpa.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.simpolor.jpa.repository.entity.Classroom;
import lombok.*;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClassroomDto {

    private Long id;
    private String name;
    private Long studentCount;

    public static ClassroomDto of(Classroom classroom){
        return ClassroomDto.builder()
                .id(classroom.getClassroomId())
                .name(classroom.getName())
                .studentCount(
                        !CollectionUtils.isEmpty(classroom.getStudents())
                        ? classroom.getStudents().stream().count()
                        : 0L
                )
                .build();
    }

    public Classroom toEntity() {

        return Classroom.builder()
                .classroomId(this.id)
                .name(this.name)
                .build();
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ClassroomDetail {

        private long id;
        private String name;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private List<StudentDto.StudentDetail> students;

        public static ClassroomDetail of(Classroom classroom){
            return ClassroomDetail.builder()
                    .id(classroom.getClassroomId())
                    .name(classroom.getName())
                    .createdAt(classroom.getCreatedAt())
                    .updatedAt(classroom.getUpdatedAt())
                    .students(
                            !CollectionUtils.isEmpty(classroom.getStudents())
                            ? classroom.getStudents().stream().map(StudentDto.StudentDetail::ofClassroom).collect(Collectors.toList())
                            : Collections.EMPTY_LIST
                    )
                    .build();
        }

        public static ClassroomDetail ofStudent(Classroom classroom){
            return ClassroomDetail.builder()
                    .id(classroom.getClassroomId())
                    .name(classroom.getName())
                    .build();
        }
    }
}