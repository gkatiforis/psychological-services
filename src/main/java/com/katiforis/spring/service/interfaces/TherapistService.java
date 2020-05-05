package com.katiforis.spring.service.interfaces;

import com.katiforis.spring.model.*;

import java.util.Date;
import java.util.List;


public interface TherapistService {
    Therapist findByTherapistId(int id);

    void saveTherapist(Therapist therapist);

    void updateTherapist(Therapist therapist);

    void saveOrUpdateTherapist(Therapist therapist);

    List<Therapist> findTherapistsByCritiria(List<Integer> disorderIds);

    void saveService(Service service);

    void removeServiceFromTherapist(Integer serviceId);

    List<Service> getTherapistServicies(Integer userId);

    List<ServiceType> getServiceTypes();

    List<WorkingHour> findAllWorkingHours();

    void setWorkingHoursToTherapist(Integer userId, List<Integer> workingHoursIds);

    List<WorkingHour> getTherapistWorkingHour(Integer userId);

    List<WorkingHour> getTherapistFreeWorkingHourInDay(Integer userId, Date date);

    List<Integer> getTherapistFreeWorkingHourPerDayCountInMonth(Integer userId);

    List<Specialite> findAllSpecialite();

    void removeSpecialiteFromTherapist(Integer userId, List<Integer> specialiteIds);

    void addSpecialiteToTherapist(Integer userId, List<Integer> specialiteIds);

    List<Specialite> getSpecialiteHave(Integer userId, Integer have);
}
