package com.example.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.springsecurity.domain.UserData;

@Repository
public interface UserRepository extends JpaRepository<UserData, Long> {

	UserData findByName(String name);
}


