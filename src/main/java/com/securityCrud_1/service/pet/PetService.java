package com.securityCrud_1.service.pet;


import com.securityCrud_1.dto.request.PetRequest;
import com.securityCrud_1.dto.response.PetResponse;

import java.util.List;

public interface PetService {

    List<PetResponse> getAllPets();

    PetResponse getPetById(Long id);

    PetResponse save(PetRequest petRequest);

    PetResponse update(PetRequest petRequest, Long id);

    void delete(Long id);

}
