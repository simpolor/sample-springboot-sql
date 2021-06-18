package io.simpolor.jpa.model;

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
public class TagDto {

    private long seq;
    private String title;

    public Tag toEntity(){

        return Tag.builder()
                .title(this.title)
                .build();
    }

    public static TagDto of(Tag tag){

        return TagDto.builder()
                .seq(tag.getSeq())
                .title(tag.getTitle())
                .build();
    }

    public static List<TagDto> of(List<Tag> tags){

        return tags.stream().map(tag -> TagDto.of(tag)).collect(Collectors.toList());
    }

}
