package com.securityCrud_1.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetRequest {

    private String name;
    private String type;
    private String color;
    private String owner;

}
