package com.securityCrud_1.controller.secured;


import com.securityCrud_1.dto.ApiResponse;
import com.securityCrud_1.dto.request.PetRequest;
import com.securityCrud_1.dto.response.PetResponse;
import com.securityCrud_1.service.pet.PetService;
import com.securityCrud_1.service.pet.PetServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/pet")
public class PetController {

    private final PetService petService;

    public PetController(PetServiceImpl petService) {
        this.petService = petService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PetResponse>>> getPets() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(
                        "success",
                        "Existing pets",
                        petService.getAllPets()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PetResponse>> createPet(@RequestBody PetRequest petRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        "success",
                        "Pet created successfully",
                        petService.save(petRequest))
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PetResponse>> getPet(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(
                        "success",
                        "Pet retrieved successfully",
                        petService.getPetById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PetResponse>> updatePet(@RequestBody PetRequest petRequest, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(
                        "success",
                        "Pet retrieved successfully",
                        petService.update(petRequest, id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePet(@PathVariable Long id) {
        petService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(
                        "success",
                        "Pet retrieved successfully",
                        null));
    }


}
