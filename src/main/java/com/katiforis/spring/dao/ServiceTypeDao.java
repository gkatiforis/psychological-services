package com.katiforis.spring.dao;

import com.katiforis.spring.model.ServiceType;

import java.util.List;

public interface ServiceTypeDao {
    List<ServiceType> findAllServiceType();
}
