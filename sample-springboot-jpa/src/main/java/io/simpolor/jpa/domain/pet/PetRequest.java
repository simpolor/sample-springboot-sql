package io.simpolor.jpa.domain.pet;

import io.simpolor.jpa.repository.entity.Pet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetRequest {

    private long seq;
    private String kind;
    private String name;
    private int age;

    public Pet toPet(){

        return Pet.builder()
                .kind(this.kind)
                .name(this.name)
                .age(this.age)
                .build();
    }
}
