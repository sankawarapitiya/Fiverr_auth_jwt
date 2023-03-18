package com.nbkarthi.auth_jwt.repository;

import com.nbkarthi.auth_jwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    Optional<User> findById(Integer integer);
    Optional<List<User>> findAllByFirstNameIsNotNull();
}
