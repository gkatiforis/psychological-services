package com.katiforis.spring.dao;

import com.katiforis.spring.model.User;

import java.util.List;


public interface UserDao {
    User findById(int id);

    User findBySSO(String sso);

    User findByEmail(String email);

    void save(User user);

    void deleteBySSO(String sso);

    List<User> findAllUsers();
}