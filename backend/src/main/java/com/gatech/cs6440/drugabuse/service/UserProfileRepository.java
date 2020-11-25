package com.gatech.cs6440.drugabuse.service;

import com.gatech.cs6440.drugabuse.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<User, Integer> {
}
