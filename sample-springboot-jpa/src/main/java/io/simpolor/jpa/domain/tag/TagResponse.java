package io.simpolor.jpa.domain.tag;

import io.simpolor.jpa.repository.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagResponse {

    private long seq;
    private String title;

    public static TagResponse of(Tag tag){

        return TagResponse.builder()
                .seq(tag.getSeq())
                .title(tag.getTitle())
                .build();
    }

    public static List<TagResponse> of(List<Tag> tags){

        return tags.stream().map(tag -> TagResponse.of(tag)).collect(Collectors.toList());
    }

}
