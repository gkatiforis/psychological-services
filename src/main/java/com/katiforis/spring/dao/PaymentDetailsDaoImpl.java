package com.katiforis.spring.dao;

import com.katiforis.spring.model.PaymentDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


@Repository("paymentDetailsDao")
public class PaymentDetailsDaoImpl extends AbstractDao<Integer, PaymentDetails> implements PaymentDetailsDao {

    static final Logger logger = LoggerFactory.getLogger(PaymentDetailsDaoImpl.class);

    public PaymentDetails findById(int id) {
        PaymentDetails paymentdatails = getByKey(id);


        return paymentdatails;
    }
}