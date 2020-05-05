package com.katiforis.spring.dao;

import com.katiforis.spring.model.Service;
import com.katiforis.spring.model.Therapist;
import com.katiforis.spring.model.WorkingHour;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository("serviceDao")
public class ServiceDaoImpl extends AbstractDao<Integer, Service> implements ServiceDao {

    static final Logger logger = LoggerFactory.getLogger(ServiceDaoImpl.class);

    public Service findById(int id) {
        Service service = getByKey(id);
        if(service!=null){
            Hibernate.initialize(service.getServiceType());
        }

        return service;
    }

    public void save(Service service) {
        persist(service);
    }

    public void update(Service service) {
        update(service);
    }

    public void saveOrUpdate(Service service) {
        saveOrUpdate(service);
    }

    public List<Service> findTherapistServicies(Integer userId){
        String hql = "from Service se where se.isDeleted=false and se.id in " +
                "(select se.id from Therapist t join t.servicies se where t.id = :userId)";

        List<Service> services = (List<Service>) getSession().createQuery(hql).setParameter("userId", userId).list();

        return services;
    }

    @SuppressWarnings("unchecked")
    public List<Service> findAllService() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Service> services = (List<Service>) criteria.list();

        return services;
    }
}