package com.ashish.threadboost.repository;


import com.ashish.threadboost.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByUserName(String userName);
}
