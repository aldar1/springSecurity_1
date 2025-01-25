package com.securityCrud_1.service.pet;

import com.securityCrud_1.dto.request.PetRequest;
import com.securityCrud_1.dto.response.PetResponse;
import com.securityCrud_1.entity.Pet;
import com.securityCrud_1.exception.PetNotFoundException;
import com.securityCrud_1.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public List<PetResponse> getAllPets() {
        List<Pet> pets = petRepository.findAll();
        return pets.stream()
                .map(PetResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public PetResponse getPetById(Long id) {
        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isPresent()) {
            return PetResponse.fromEntity(pet.get());
        }
        throw new PetNotFoundException("Pet not found with ID: " + id);
    }

    @Override
    public PetResponse save(PetRequest petRequest) {
        Pet pet = toEntity(petRequest);
        petRepository.save(pet);
        return PetResponse.fromEntity(pet);
    }

    @Override
    public PetResponse update(PetRequest petRequest, Long id) {
        if (petRequest == null) {
            throw new IllegalArgumentException("Pet request no puede ser nulo");
        }
        Pet pet = petRepository.findById(id).orElseThrow(() -> new PetNotFoundException("Pet not found with ID: " + id));
        if (petRequest.getName() != null && !petRequest.getName().isEmpty()) {
            pet.setName(petRequest.getName());
        }
        if (petRequest.getType() != null && !petRequest.getType().isEmpty()) {
            pet.setType(petRequest.getType());
        }
        if (petRequest.getColor() != null && !petRequest.getColor().isEmpty()) {
            pet.setColor(petRequest.getColor());
        }
        if (petRequest.getOwner() != null && !petRequest.getOwner().isEmpty()) {
            pet.setOwner(petRequest.getOwner());
        }
        petRepository.save(pet);
        return PetResponse.fromEntity(pet);
    }

    @Override
    public void delete(Long id) {
        Pet pet = petRepository.findById(id).orElseThrow(() -> new PetNotFoundException("Pet not found with ID: " + id));
        petRepository.delete(pet);
    }

    private Pet toEntity(PetRequest petRequest) {
        return Pet.builder()
                .name(petRequest.getName())
                .type(petRequest.getType())
                .color(petRequest.getColor())
                .owner(petRequest.getOwner())
                .build();
    }

}
