package com.gatech.cs6440.drugabuse.service;

import com.gatech.cs6440.drugabuse.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {

}
