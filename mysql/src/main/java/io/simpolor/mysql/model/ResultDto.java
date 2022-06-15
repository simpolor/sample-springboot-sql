package io.simpolor.mysql.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResultDto {

    private Long id;

    public static ResultDto of(Long id){
        return ResultDto.builder()
                .id(id)
                .build();
    }
}