package com.asutosh.expense_tracker.repository;

import com.asutosh.expense_tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository
        extends JpaRepository<User, Long> {

}