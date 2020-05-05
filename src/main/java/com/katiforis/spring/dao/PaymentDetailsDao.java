package com.katiforis.spring.dao;

import com.katiforis.spring.model.PaymentDetails;


public interface PaymentDetailsDao {
    PaymentDetails findById(int id);
}