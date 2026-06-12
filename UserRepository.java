package com.redpulse.repository;

import com.redpulse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    List<User> findByRoleAndBloodtype(String donor, String bloodtype);

    List<User> findByRole(String donor);

    List<User> countByRole(String donor);
}
