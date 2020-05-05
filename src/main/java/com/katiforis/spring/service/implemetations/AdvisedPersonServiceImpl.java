package com.katiforis.spring.service.implemetations;

import com.katiforis.spring.dao.*;
import com.katiforis.spring.model.*;
import com.katiforis.spring.service.interfaces.AdvisedPersonService;
import com.katiforis.spring.service.interfaces.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


@Service("advisedPersonService")
@Transactional
public class AdvisedPersonServiceImpl implements AdvisedPersonService {

    @Autowired
    private PersonDao daoAdvisedPerson;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ServiceDao serviceDao;

    @Autowired
    private NotificationService notificationService;

    public AdvisedPerson findByAdvisedPersonId(int id){
        return daoAdvisedPerson.findById(id);
    }

    public void updateAdvisedPerson(AdvisedPerson advisedPerson){
        AdvisedPerson entity = daoAdvisedPerson.findById(advisedPerson.getId());
        if(entity!=null){
            entity.setLast_name(advisedPerson.getLast_name());
            entity.setFirst_name(advisedPerson.getFirst_name());
            entity.setDescription(advisedPerson.getDescription());
            //entity.setPhone(advisedPerson.getPhone());

        }
    }

    public void buyService(Integer userId, Integer serviceId) {

        com.katiforis.spring.model.Service service = serviceDao.findById(serviceId);
        AdvisedPerson advisedPerson = daoAdvisedPerson.findById(userId);
        Therapist therapist =  service.getTherapist();

        BigDecimal advisedPersonAmountmount = advisedPerson.getAmount();
        BigDecimal serviceAmount = service.getAmount();
        BigDecimal sub = advisedPersonAmountmount.subtract(serviceAmount);

        if(sub.compareTo(BigDecimal.ZERO) >= 0){
            advisedPerson.setAmount(sub);
        }
        else{
            advisedPerson.setAmount(new BigDecimal(0.0));
        }

        Order order = new Order( advisedPerson, therapist, service, 0,   new Orderstatus(1));
        orderDao.save(order);
        notificationService.notifBuyService(advisedPerson, therapist);
    }

    public List<Order> findUserOrdersByTherapist(Integer userId, Integer therapistId){
        List<Order> orders =  orderDao.findUserOrdersByTherapist(userId, therapistId);
        return orders;
    }
}


