package io.simpolor.jpa.domain.tag;

import io.simpolor.jpa.repository.entity.Tag;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagRequest {

    private String title;
    private Integer orderNum;

    public Tag toTag(){

        return Tag.builder()
                .title(this.title)
                .orderNum(this.orderNum)
                .build();
    }
}
