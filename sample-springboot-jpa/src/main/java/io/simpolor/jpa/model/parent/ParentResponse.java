package io.simpolor.jpa.model.parent;

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
public class ParentResponse {

    private long seq;
    private String fatherName;
    private String motherName;
    private String studentName;

    public static ParentResponse of(Parent parent){

        return ParentResponse.builder()
                .seq(parent.getSeq())
                .fatherName(parent.getFatherName())
                .motherName(parent.getMotherName())
                .studentName(Objects.nonNull(parent.getStudent()) ? parent.getStudent().getName() : null )
                .build();
    }

    public static List<ParentResponse> of(List<Parent> parents){

        return parents.stream().map(p -> ParentResponse.of(p)).collect(Collectors.toList());
    }

}
