package com.katiforis.spring.dao;

import com.katiforis.spring.model.Specialite;

import java.util.List;

public interface SpecialiteDao {
    List<Specialite> findAllSpecialite();
    List<Specialite> findSpecialiteTerapistNotHave(Integer userId);
    List<Specialite> findSpecialiteTerapistHave(Integer userId);
}
