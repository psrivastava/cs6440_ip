package com.gatech.cs6440.drugabuse.service;

import com.gatech.cs6440.drugabuse.controller.APIController;
import com.gatech.cs6440.drugabuse.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService implements UserProfileRepository {

    @Autowired
    UserProfileRepository userProfileRepository;

    Logger logger = LoggerFactory.getLogger(UserProfileService.class);

    public UserProfileService() {
    }

    @Override
    public List<User> findAll() {
        return userProfileRepository.findAll();
    }

    @Override
    public List<User> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<User> findAllById(Iterable<Integer> iterable) {
        return userProfileRepository.findAllById(iterable);
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {
        userProfileRepository.deleteById(integer);
    }

    @Override
    public void delete(User user) {
        userProfileRepository.delete(user);
    }

    @Override
    public void deleteAll(Iterable<? extends User> iterable) {
        userProfileRepository.deleteAll(iterable);
    }

    @Override
    public void deleteAll() {
        userProfileRepository.deleteAll();
    }

    @Override
    public <S extends User> S save(S s) {
        return userProfileRepository.save(s);
    }

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> iterable) {
        return userProfileRepository.saveAll(iterable);
    }

    @Override
    public Optional<User> findById(Integer integer) {

        logger.info("User profile for id:" + integer);
        return userProfileRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return userProfileRepository.existsById(integer);
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends User> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<User> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public User getOne(Integer integer) {
        return null;
    }

    @Override
    public <S extends User> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends User> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends User> boolean exists(Example<S> example) {
        return false;
    }
}
