package com.securityCrud_1.dto.response;


import com.securityCrud_1.entity.Pet;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PetResponse {

    private Integer id;
    private String name;
    private String type;
    private String color;
    private String owner;

    public static PetResponse fromEntity(Pet pet) {
        return PetResponse.builder()
                .name(pet.getName())
                .type(pet.getType())
                .color(pet.getColor())
                .owner(pet.getOwner())
                .build();
    }

}
