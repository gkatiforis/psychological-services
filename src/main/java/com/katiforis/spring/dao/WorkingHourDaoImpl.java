package com.katiforis.spring.dao;

import com.katiforis.spring.model.WorkingHour;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

@Repository("daoWorkingHour")
public class WorkingHourDaoImpl extends AbstractDao<Integer, WorkingHour>  implements WorkingHourDao {

    public WorkingHour findById(int id) {
        WorkingHour workingHour = getByKey(id);
        return workingHour;
    }

    @Override
    public List<WorkingHour> findAllWorkingHours() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<WorkingHour> workingHours = (List<WorkingHour>) criteria.list();
        return workingHours;
    }

    @Override
    public List<WorkingHour> findTherapistWorkingHours(Integer userId){
          String hql = "from WorkingHour wh where wh.id in " +
           "(select wh.id from Therapist t join t.workingHours wh where t.id = :userId)";
        List<WorkingHour> workingHours = (List<WorkingHour>) getSession().createQuery(hql).setParameter("userId", userId).list();
        return workingHours;
    }

    @Override
    public List<WorkingHour> findTherapistFreeWorkingHours(Integer userId, Date date){

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if(day_of_week <=0) day_of_week = 7;

        String hql = "from WorkingHour wh where wh.dayId = :day_of_week and wh.id in " +
                "(select wh.id from Therapist t join t.workingHours wh where t.id = :userId and " +
                "wh.id not in" +
                "(select c.workingHour.id from Conference c where c.therapist.id = :userId and" +
                " c.conferencestatus = (from Conferencestatus cs where cs.id = 1) and" +
                " c.date = :selectedDate" +
                "))";

        List<WorkingHour> workingHours = (List<WorkingHour>) getSession().createQuery(hql)
                .setParameter("userId", userId)
                .setParameter("selectedDate", date)
                .setParameter("day_of_week", day_of_week)
                .list();

        return workingHours;

    }
}
