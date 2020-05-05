package com.katiforis.spring.service.implemetations;

import com.katiforis.spring.dao.*;
import com.katiforis.spring.model.*;
import com.katiforis.spring.service.interfaces.NotificationService;
import com.katiforis.spring.service.interfaces.TherapistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service("therapistService")
@Transactional
public class TherapistServiceImpl implements TherapistService {

    @Autowired
    private TherapistDao daoTherapist;

    @Autowired
    private ServiceDao daoService;

    @Autowired
    private SpecialiteDao daoSpecialite;

    @Autowired
    private WorkingHourDao daoWorkingHours;

    @Autowired
    private ServiceTypeDao serviceTypeDao;

    public void saveTherapist(Therapist therapist) {

        daoTherapist.save(therapist);
    }
    public void updateTherapist(Therapist therapist) {

        Therapist entity = daoTherapist.findById(therapist.getId());
        if(entity!=null){
            entity.setDescription(therapist.getDescription());
            entity.setFirst_name(therapist.getFirst_name());
            entity.setLast_name(therapist.getLast_name());
            entity.setPricePerHour(therapist.getPricePerHour());
            entity.setSpecialisation(therapist.getSpecialisation());
            entity.setPhone(therapist.getPhone());
            entity.setBio(therapist.getBio());
        }
    }

    public void saveService(com.katiforis.spring.model.Service service) {

        daoService.save(service);
    }

    public void saveOrUpdateTherapist(Therapist therapist){
        daoTherapist.saveOrUpdate(therapist);
    }

    public List<Therapist> findTherapistsByCritiria(List<Integer> disordersIds){
        return daoTherapist.findTherapistByCriteria(disordersIds);
    }

    public Therapist findByTherapistId(int id) {
        return daoTherapist.findById(id);
    }

    public void setWorkingHoursToTherapist(Integer userId,   List<Integer> workingHoursIds) {
        Therapist therapist = daoTherapist.findById(userId);
        therapist.getWorkingHours().clear();
        workingHoursIds.forEach(id -> therapist.getWorkingHours().add(new WorkingHour(id)) );
    }

    public void removeServiceFromTherapist(Integer serviceId) {
        com.katiforis.spring.model.Service service = daoService.findById(serviceId);
        service.setDeleted(true);
    }

    public List<Specialite> findAllSpecialite(){
        return daoSpecialite.findAllSpecialite();
    }

    public void addSpecialiteToTherapist(Integer userId,   List<Integer> SpecialiteIds) {
        Therapist therapist = daoTherapist.findById(userId);
        SpecialiteIds.forEach(id -> therapist.getSpecialities().add(new Specialite(id,"")) );
    }
    public void removeSpecialiteFromTherapist(Integer userId,   List<Integer> SpecialiteIds) {
        Therapist therapist = daoTherapist.findById(userId);
        SpecialiteIds.forEach(id -> therapist.getSpecialities().removeIf(specialite -> specialite.getId() == id));
    }

    public List<Specialite> getSpecialiteHave(Integer userId, Integer have){

        if(have == 0){
            return daoSpecialite.findSpecialiteTerapistNotHave(userId);
        }
        else if(have == 1){
            return daoSpecialite.findSpecialiteTerapistHave(userId);
        }

        return null;
    }


    public List<WorkingHour> getTherapistWorkingHour(Integer userId){
        return daoWorkingHours.findTherapistWorkingHours(userId);
    }

    public  List<com.katiforis.spring.model.Service> getTherapistServicies(Integer userId){
        return daoService.findTherapistServicies(userId);
    }

    public List<ServiceType> getServiceTypes(){
        return serviceTypeDao.findAllServiceType();
    }

    public List<WorkingHour> getTherapistFreeWorkingHourInDay(Integer userId, Date date){
        return daoWorkingHours.findTherapistFreeWorkingHours(userId, date);
    }

    public List<Integer> getTherapistFreeWorkingHourPerDayCountInMonth(Integer userId){
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        List<Integer> counts = new ArrayList<>();
         int count = 30;
         int i;
         for(i = 0; i<count; i++){
             List<WorkingHour> workingHours = daoWorkingHours.findTherapistFreeWorkingHours(userId, date);
             counts.add(workingHours.size());
             c.add(Calendar.DATE, 1);
             date = c.getTime();
         }

        return counts;
    }
    public List<WorkingHour> findAllWorkingHours(){
        return daoWorkingHours.findAllWorkingHours();
    }
}
