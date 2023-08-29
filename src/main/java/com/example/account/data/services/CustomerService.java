package com.example.account.data.services;
import com.example.account.data.entities.Customer;
import com.example.account.data.handler.request.CustomerDto;
import com.example.account.data.handler.request.TransferRequest;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    Map<String, Object> updateData(int id, CustomerDto CustomerDto);

    Map<String, Object> createNasabah(CustomerDto customerDto);

    Map<String,Object> transferSaldo(TransferRequest transferRequest);

    List<Customer> findAll();

    Map<String,Object>updateSaldo(int id, CustomerDto customerDto);
}
