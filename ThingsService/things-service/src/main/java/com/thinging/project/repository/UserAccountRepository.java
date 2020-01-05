package com.thinging.project.repository;

import com.thinging.project.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    Optional<UserAccount> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<UserAccount> findByFirstName(String firstName);
}
