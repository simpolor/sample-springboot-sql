package io.simpolor.jpa.domain.parent;

import io.simpolor.jpa.repository.entity.Parent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParentRequest {

    private long seq;
    private String fatherName;
    private String motherName;

    private List<String> hobbies = new ArrayList<>();
    private Parent parent;

    public Parent toParent() {

        return Parent.builder()
                .fatherName(this.fatherName)
                .motherName(this.motherName)
                .build();
    }

    public Parent toParent(long seq) {

        return Parent.builder()
                .seq(seq)
                .fatherName(this.fatherName)
                .motherName(this.motherName)
                .build();
    }
}
