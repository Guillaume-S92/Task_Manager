package com.portfolio.SecureConnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.SecureConnect.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
