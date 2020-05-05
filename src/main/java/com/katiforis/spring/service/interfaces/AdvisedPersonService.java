package com.katiforis.spring.service.interfaces;

import com.katiforis.spring.Respones.JsonRespone;
import com.katiforis.spring.Respones.NotificationResponse;
import com.katiforis.spring.model.*;

import java.util.Date;
import java.util.List;


public interface AdvisedPersonService {
    void updateAdvisedPerson(AdvisedPerson advisedPerson);
    AdvisedPerson findByAdvisedPersonId(int id);
    void buyService(Integer userId, Integer serviceId);
    List<Order> findUserOrdersByTherapist(Integer userId, Integer therapistId);
}
