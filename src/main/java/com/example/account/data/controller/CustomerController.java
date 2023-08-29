package com.example.account.data.controller;

import com.example.account.data.entities.Customer;
import com.example.account.data.handler.request.CustomerDto;
import com.example.account.data.handler.request.TransferRequest;
import com.example.account.data.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/api")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/createNasabah", method = RequestMethod.POST)
    public Map<String,Object> createNasabah(@RequestBody CustomerDto customerDto){
        return customerService.createNasabah(customerDto);
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    public Map<String,Object> transferNasabah(@RequestBody TransferRequest transferRequest){
        return customerService.transferSaldo(transferRequest);
    }

    @PutMapping("/update/{id}")
    public Map<String, Object> updateData (@PathVariable("id")int id ,@RequestBody CustomerDto CustomerDto){
        return customerService.updateData(id, CustomerDto);
    }
    @GetMapping("/findAll")
    public List<Customer> findAll (){
        return customerService.findAll();
    }
    @PutMapping("/updatesaldo/{id}")
    public Map<String,Object > updateSaldo (@PathVariable("id")int id ,@RequestBody CustomerDto CustomerDto) {
        return customerService.updateSaldo(id, CustomerDto);
    }
}
