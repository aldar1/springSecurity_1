package com.securityCrud_1.repository;


import com.securityCrud_1.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

//    @Query("SELECT user FROM AuthUser user WHERE user.username = :username")
//    public AuthUser findByUsername(@Param("username") String username);

    AuthUser findByUsername(String username);
}
