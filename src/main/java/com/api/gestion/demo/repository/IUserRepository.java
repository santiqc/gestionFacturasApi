package com.api.gestion.demo.repository;

import com.api.gestion.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

        Optional<User> findUserByEmail(String email);
}
