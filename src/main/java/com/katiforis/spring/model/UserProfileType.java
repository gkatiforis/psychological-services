package com.katiforis.spring.model;

import java.io.Serializable;

public enum UserProfileType implements Serializable{
    USER("PERSON"),
    DBA("EXPERT"),
    ADMIN("ADMIN");

    String userProfileType;

    private UserProfileType(String userProfileType){
        this.userProfileType = userProfileType;
    }

    public String getUserProfileType(){
        return userProfileType;
    }

}