package com.katiforis.spring.dao;

import com.katiforis.spring.model.Service;
import org.hibernate.Criteria;
import com.katiforis.spring.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("orderDao")
public class OrderDaoImpl extends AbstractDao<Integer, Order> implements OrderDao {

    static final Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class);

    public Order findById(int id) {
        Order order = getByKey(id);


        return order;
    }

    public void save(Order order) {
        persist(order);
    }

    public void update(Order order) {
        update(order);
    }
    public void saveOrUpdate(Order order) {
        saveOrUpdate(order);
    }

    public List<Order> findUserOrdersByTherapist(Integer userId, Integer therapistId){
        String hql = "select distinct o from Order o left join fetch o.service a " +
                " where o.orderStatus.id = 1 and o.therapist.id = :therapistId and o.advisedPerson.id = :userId ";

        List<Order> orders = (List<Order>) getSession().createQuery(hql)
                .setParameter("userId", userId)
                .setParameter("therapistId", therapistId)
                .list();

        return orders;
    }

    public List<Order> findUserOrdersLiveOrders(Integer userId){
        String hql = "select distinct o from Order o left join fetch o.service a" +
                " left join fetch  o.orderStatus " +
                " left join fetch  o.advisedPerson " +
                " left join fetch  o.therapist " +
                " left join fetch  o.conferences cc" +
                " left join fetch  cc.conferencestatus" +
                " left join fetch  cc.conferencesType" +
                " where (o.orderStatus.id=1 or o.orderStatus.id=4) and (o.advisedPerson.id = :userId or o.therapist.id = :userId) ";

        List<Order> orders = (List<Order>) getSession().createQuery(hql)
                .setParameter("userId", userId)
                .list();

        return orders;
    }

    public List<Order> findUserOrdersCompletedOrders(Integer userId){
        String hql = "select distinct o from Order o left join fetch o.service a" +
                " left join fetch  o.orderStatus " +
                " where (o.orderStatus.id=2) and o.advisedPerson.id = :userId ";

        List<Order> orders = (List<Order>) getSession().createQuery(hql)
                .setParameter("userId", userId)
                .list();

        return orders;
    }
}