package com.example.account.data.services;

import com.example.account.data.entities.Customer;
import com.example.account.data.handler.request.CustomerDto;
import com.example.account.data.handler.request.TransferRequest;
import com.example.account.data.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Map<String, Object> updateData(int id, CustomerDto CustomerDto) {
        var updateCustomer = customerRepository.findById(id);
        Map<String, Object> result = new HashMap<>();
        if(updateCustomer.get() == null ){
            result.put("id tidak ditemukan" , updateCustomer.get().getId());
            return result;
        }
        updateCustomer.get().setPhone(CustomerDto.getPhone());
        updateCustomer.get().setName(CustomerDto.getName());
        updateCustomer.get().setAlamat(CustomerDto.getAlamat());
        customerRepository.save(updateCustomer.get());
        result.put("Status " , HttpStatus.OK);
        result.put("Messages" ,updateCustomer.get());
        return result;
    }

    @Override
    public Map<String, Object> createNasabah(CustomerDto customerDto) {
        Map<String, Object> result = new HashMap<>();
        try {
//            Customer customer = new Customer();
            var customer = new Customer();
            customer.setName(customerDto.getName());
            customer.setAlamat(customerDto.getAlamat());
            customer.setPhone(customerDto.getPhone());
            Random random = new Random();
            int noRek = random.nextInt(10000);
            customer.setNoRekening(String.valueOf(noRek));
            if (customerDto.getSaldo() < 50000){
                result.put("status", "failed");
                result.put("message", "Saldo minimal Rp. 50.000");
                return result;
            }else {
                customer.setSaldo(customerDto.getSaldo());
            }
            customerRepository.save(customer);
            result.put("status", "success");
            result.put("message", customer);
            return result;
        } catch (Exception e) {
            result.put("status", "failed");
            result.put("message", "Gagal menambahkan data");
            return result;
        }
    }

    @Override
    public Map<String,Object> transferSaldo( TransferRequest transferRequest){
        Map<String, Object> result = new HashMap<>();
        int saldo = 50000;
        int chargeFee = 6500;
        try {
            Customer customer = customerRepository.findByNoRekening(transferRequest.getNoRekening());
            Customer customer2 = customerRepository.findByNoRekening(transferRequest.getNoRekening2());
            if(customer.getNoRekening().equalsIgnoreCase(customer2.getNoRekening())) {
                result.put("status", "failed");
                result.put("message", "Tidak bisa transfer ke rekening sendiri");
                return result;
            }
            if (customer.getSaldo() < transferRequest.getSaldo()){
                result.put("status", "failed");
                result.put("message", "Saldo tidak cukup");
                return result;
            }  else if (transferRequest.getSaldo() < saldo ) {

                result.put("status", "failed");
                result.put("message", "transfer tidak boleh kurang dari Rp. 50.000");
                return result;
            } else if(customer.getSaldo() < transferRequest.getSaldo() + chargeFee){

                result.put("status", "failed");
                result.put("message", "transfer tidak mencukupi");
                return result;
            }
            else {
                customer.setSaldo(customer.getSaldo() - transferRequest.getSaldo());
                customer.setSaldo(customer.getSaldo() - chargeFee);
                customer2.setSaldo(customer2.getSaldo() + transferRequest.getSaldo());
                customerRepository.save(customer);
                customerRepository.save(customer2);
                result.put("status", "success");
                result.put("Pengirim", customer);
                result.put("Penerima", customer2);
                return result;
            }
        } catch (Exception e) {
            result.put("status", "failed");
            result.put("message", "Gagal transfer");
            return result;

        }
    }
    @Override
    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    @Override
    public Map<String,Object>updateSaldo(int id, CustomerDto customerDto){
        var UpdateCustomer = customerRepository.findById(id);
        Map<String , Object> result = new HashMap<>();
        if (UpdateCustomer.get()== null){
            result.put("Id tidak ditemukan", UpdateCustomer.get().getId());
            return result;
        }
        UpdateCustomer.get().setSaldo(UpdateCustomer.get().getSaldo() + customerDto.getSaldo());
        result.put("Succes" , HttpStatus.OK);
        result.put("Massage","Saldo di tambahkan menjadi" + UpdateCustomer.get().getSaldo());
        customerRepository.save(UpdateCustomer.get());
        return result;
    }


}
