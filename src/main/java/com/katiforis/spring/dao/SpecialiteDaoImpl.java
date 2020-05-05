package com.katiforis.spring.dao;

import com.katiforis.spring.model.Specialite;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("daoSpecialies")
public class SpecialiteDaoImpl extends AbstractDao<Integer, Specialite>  implements SpecialiteDao {

    @Override
    public List<Specialite> findAllSpecialite() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Specialite> specialites = (List<Specialite>) criteria.list();
        return specialites;
    }

    @Override
    public List<Specialite> findSpecialiteTerapistNotHave(Integer userId){

        String hql = "from Specialite s where s.id not in " +
                "(select s.id from Therapist t join t.specialities s where t.id = :userId)";

        List<Specialite> specialites = (List<Specialite>) getSession().createQuery(hql).setParameter("userId", userId).list();

        return specialites;
    }

    @Override
    public List<Specialite> findSpecialiteTerapistHave(Integer userId){

        String hql = "from Specialite s where s.id in " +
                "(select s.id from Therapist t join t.specialities s where t.id = :userId)";

        List<Specialite> specialites = (List<Specialite>) getSession().createQuery(hql).setParameter("userId", userId).list();

        return specialites;
    }
}
