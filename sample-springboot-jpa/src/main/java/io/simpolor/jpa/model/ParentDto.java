package io.simpolor.jpa.model;

import io.simpolor.jpa.repository.entity.Parent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParentDto {

    private long seq;
    private String fatherName;
    private String motherName;
    private String studentName;

    public Parent toEntity() {

        return Parent.builder()
                .fatherName(this.fatherName)
                .motherName(this.motherName)
                .build();
    }

    public static ParentDto of(Parent parent){

        return ParentDto.builder()
                .seq(parent.getSeq())
                .fatherName(parent.getFatherName())
                .motherName(parent.getMotherName())
                .studentName(Objects.nonNull(parent.getStudent()) ? parent.getStudent().getName() : null )
                .build();
    }

    public static List<ParentDto> of(List<Parent> parents){

        return parents.stream().map(p -> ParentDto.of(p)).collect(Collectors.toList());
    }

}
