package com.securityCrud_1.repository;

import com.securityCrud_1.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
