package io.simpolor.jpa.model.tag;

import io.simpolor.jpa.repository.entity.Tag;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagRequest {

    private Long seq;
    private String title;

    public Tag toInsert(){

        return Tag.builder()
                .title(this.title)
                .build();
    }

    public Tag toUpdate(){

        return Tag.builder()
                .seq(this.seq)
                .title(this.title)
                .build();
    }
}
