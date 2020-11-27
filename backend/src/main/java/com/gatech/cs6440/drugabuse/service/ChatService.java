package com.gatech.cs6440.drugabuse.service;


import com.gatech.cs6440.drugabuse.model.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatService implements ChatRepository{

    @Autowired
    ChatRepository chatRepository;

    @Override
    public List<Chat> findAll() {
        return chatRepository.findAll();
    }

    @Override
    public List<Chat> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Chat> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Chat> findAllById(Iterable<Integer> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Chat category) {

    }

    @Override
    public void deleteAll(Iterable<? extends Chat> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Chat> S save(S s) {
        return chatRepository.save(s);
    }

    @Override
    public <S extends Chat> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Chat> findById(Integer integer) {
        return chatRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Chat> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Chat> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Chat getOne(Integer integer) {
        return null;
    }

    @Override
    public <S extends Chat> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Chat> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Chat> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Chat> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Chat> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Chat> boolean exists(Example<S> example) {
        return false;
    }
}
