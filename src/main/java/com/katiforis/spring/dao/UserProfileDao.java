package com.katiforis.spring.dao;

import com.katiforis.spring.model.UserProfile;

        import java.util.List;


public interface UserProfileDao {
    List<UserProfile> findAll();

    UserProfile findByType(String type);

    UserProfile findById(int id);
}