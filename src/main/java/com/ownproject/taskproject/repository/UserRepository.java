package com.ownproject.taskproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ownproject.taskproject.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

}
