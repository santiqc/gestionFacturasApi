package com.api.gestion.demo.security.repository;

import com.api.gestion.demo.entitys.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    @Query(value = "SELECT * FROM User WHERE email = :email", nativeQuery = true)
    Optional<UserEntity> findByEmail(@Param("email") String email);
}
