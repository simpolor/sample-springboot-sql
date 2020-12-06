package io.simpolor.jpa.domain.parent;

import io.simpolor.jpa.repository.entity.Parent;
import io.simpolor.jpa.repository.entity.Student;
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

        Parent parent = new Parent();
        parent.setFatherName(this.fatherName);
        parent.setMotherName(this.motherName);

        return parent;
    }

    public Parent toParent(long seq) {

        Parent parent = new Parent();
        parent.setSeq(seq);
        parent.setFatherName(this.fatherName);
        parent.setMotherName(this.motherName);

        return parent;
    }
}
