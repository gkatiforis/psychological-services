package com.katiforis.spring.dao;

import com.katiforis.spring.model.AdvisedPerson;
import com.katiforis.spring.model.Therapist;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("personDao")
public class PersonDaoImpl extends AbstractDao<Integer, AdvisedPerson> implements PersonDao {

    static final Logger logger = LoggerFactory.getLogger(PersonDaoImpl.class);

    public AdvisedPerson findById(int id) {
        AdvisedPerson advisedPerson = getByKey(id);

        return advisedPerson;
    }

    @SuppressWarnings("unchecked")
    public List<AdvisedPerson> findAllAdvisedPerson() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<AdvisedPerson> advisedPerson = (List<AdvisedPerson>) criteria.list();

        return advisedPerson;
    }

    public void save(AdvisedPerson advisedPerson) {
        persist(advisedPerson);
    }

}