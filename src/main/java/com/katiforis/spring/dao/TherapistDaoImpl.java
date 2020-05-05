package com.katiforis.spring.dao;

import com.katiforis.spring.model.Therapist;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository("therapistDao")
public class TherapistDaoImpl extends AbstractDao<Integer, Therapist> implements TherapistDao {

    static final Logger logger = LoggerFactory.getLogger(TherapistDaoImpl.class);

    public Therapist findById(int id) {
        Therapist therapist = getByKey(id);
        return therapist;
    }

    @SuppressWarnings("unchecked")
    public List<Therapist> findAllTherapist() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Therapist> therapist = (List<Therapist>) criteria.list();

        return therapist;
    }

    @SuppressWarnings("unchecked")
    public List<Therapist> findTherapistByCriteria(List<Integer> disorderIds){
        Map<String, Object> paramslist = new HashMap<String,Object>();
      //  paramslist.put("skillIds",skillIds);
        String hql="";


         if(disorderIds.isEmpty()){
             hql = "select distinct t from Therapist t " +
                     "left join fetch  t.specialities s ";
         }else{
             hql = "select distinct t from Therapist t " +
                     "left join fetch  t.specialities s " +
                     "left join fetch  t.disorders d " +
                     "where d.id in (:disorderIds)";

         }

        Query  query = getSession().createQuery(hql);

        if(!disorderIds.isEmpty()) {
            query.setParameterList("disorderIds", disorderIds);
        }
        List<Therapist> therapists = (List<Therapist>) query.list();

        return therapists;
    }

    public void save(Therapist therapist) {
        persist(therapist);
    }

    public void update(Therapist therapist) {
        update(therapist);
    }

    public void saveOrUpdate(Therapist therapist) {
        saveOrUpdate(therapist);
    }

}