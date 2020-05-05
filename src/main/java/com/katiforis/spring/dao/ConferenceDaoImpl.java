package com.katiforis.spring.dao;

import com.katiforis.spring.model.Conference;
import com.katiforis.spring.model.Therapist;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository("conferenceDao")
public class ConferenceDaoImpl extends AbstractDao<Integer, Conference> implements ConferenceDao {

    public Conference findById(int id) {
        Conference conference = getByKey(id);
        return conference;
    }

    public Conference findByIdFull(int id) {
        Conference conference = getByKey(id);

        if(conference!=null){
            Hibernate.initialize(conference.getAdvisedPerson());
            Hibernate.initialize(conference.getTherapist());
        }

        return conference;
    }

    public void save(Conference conference) {
        persist(conference);
    }

    public void update(Conference conference) {
        update(conference);
    }
    public void saveOrUpdate(Conference conference) {
        saveOrUpdate(conference);
    }

    public List<Conference> findConferenceByCritiria(Integer userId, Integer conferencestatus){
        String hql="";

             hql = "select c from Conference c " +
                     " left join fetch c.therapist " +
                     " left join fetch c.advisedPerson " +
                     " left join fetch c.order co" +
                     " left join fetch co.service " +
                     " left join fetch c.conferencestatus " +
                     " left join fetch c.conferencesType " +
                     "where (" +
                     "c.advisedPerson.id  = :userId or c.therapist.id = :userId) and c.conferencestatus.id = :conferencestatus order by c.date";
        Query  query = getSession().createQuery(hql);
        List<Conference> conferences = (List<Conference>) query
                .setParameter("userId", userId)
                .setParameter("conferencestatus", conferencestatus)
                .list();
        return conferences;
    }

    public  List<Conference>  findConferenceCountPerDay(Integer userId, Date date ){
        String hql="";

        hql = "select c from Conference c " +
                "where (" +
                "c.advisedPerson.id  = :userId or c.therapist.id = :userId) and c.conferencestatus.id = 1" +
                " and c.date = :date group by c.workingHour";

        Query  query = getSession().createQuery(hql);

        List<Conference> conferences = (List<Conference>) query
                .setParameter("userId", userId)
                .setParameter("date", date)
                .list();
        return conferences;
    }
}