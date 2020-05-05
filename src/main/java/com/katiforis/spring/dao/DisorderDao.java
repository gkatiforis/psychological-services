package com.katiforis.spring.dao;

import com.katiforis.spring.model.Disorder;

import java.util.List;

public interface DisorderDao {
    List<Disorder> findAllDisorders();
    List<Disorder> findDisordersUserNotHave(Integer userId);
    List<Disorder> findDisordersUserHave(Integer userId);
}
