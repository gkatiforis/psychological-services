package com.katiforis.spring.dao;

import com.katiforis.spring.model.Disorder;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("daoDisorders")
public class DisorderDaoImpl extends AbstractDao<Integer, Disorder>  implements DisorderDao {

    @Override
    public List<Disorder> findAllDisorders() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Disorder> disorders = (List<Disorder>) criteria.list();
        return disorders;
    }

    @Override
    public List<Disorder> findDisordersUserNotHave(Integer userId){

        String hql = "from Disorder s where s.id not in " +
                "(select s.id from User t join t.disorders s where t.id = :userId)";

        List<Disorder> disorders = (List<Disorder>) getSession().createQuery(hql).setParameter("userId", userId).list();

        return disorders;
    }

    @Override
    public List<Disorder> findDisordersUserHave(Integer userId){

        String hql = "from Disorder s where s.id in " +
                "(select s.id from User t join t.disorders s where t.id = :userId)";

        List<Disorder> disorders = (List<Disorder>) getSession().createQuery(hql).setParameter("userId", userId).list();

        return disorders;
    }
}
