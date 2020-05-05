package com.katiforis.spring.service.interfaces;

import com.katiforis.spring.model.UserProfile;

        import java.util.List;

public interface UserProfileService {

    UserProfile findById(int id);

    UserProfile findByType(String type);

    List<UserProfile> findAll();
}