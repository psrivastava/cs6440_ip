package com.gatech.cs6440.drugabuse.service;

import com.gatech.cs6440.drugabuse.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserRepository {

    @Autowired
    UserRepository userRepository;

    public UserService() {
    }

    @Override
    public <S extends UserDetails> S save(S s) {
        return null;
    }

    @Override
    public <S extends UserDetails> List<S> saveAll(Iterable<S> iterable) {
        return userRepository.saveAll(iterable);
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends UserDetails> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<UserDetails> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public UserDetails getOne(Integer integer) {
        return null;
    }

    @Override
    public <S extends UserDetails> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends UserDetails> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends UserDetails> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends UserDetails> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends UserDetails> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends UserDetails> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public Optional<UserDetails> findById(Integer integer) {
        return userRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return userRepository.existsById(integer);
    }

    @Override
    public List<UserDetails> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<UserDetails> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<UserDetails> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<UserDetails> findAllById(Iterable<Integer> iterable) {
        return userRepository.findAllById(iterable);
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        userRepository.deleteById(integer);
    }

    @Override
    public void delete(UserDetails userDetails) {
        userRepository.delete(userDetails);
    }

    @Override
    public void deleteAll(Iterable<? extends UserDetails> iterable) {
        userRepository.deleteAll(iterable);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }
}
