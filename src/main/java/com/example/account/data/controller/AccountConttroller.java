package com.example.account.data.controller;

import com.example.account.data.handler.renspon.AccountdataRespon;
import com.example.account.data.handler.renspon.CreateAccountdataRespon;
import com.example.account.data.handler.request.CreateAccountdataDto;
import com.example.account.data.services.AccountdataServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
////TODO CLASS CONTOLLER UNTUK MENGONTROL UPDATE,DELTE,CREATED, DI LAYAR INTERFACE REST API.....
@RestController
    @RequestMapping("api-v1/account")
    public class AccountConttroller {
    @Autowired
    private AccountdataServices accountdataServices;
    @PostMapping("create")
        public ResponseEntity createAccountdata(@RequestBody CreateAccountdataDto createAccountdataDTO) {
        String result = accountdataServices.CreatedAccountdata(createAccountdataDTO);
        CreateAccountdataRespon Respon = new CreateAccountdataRespon();
        Respon.setResultMassage(result);
        Respon.setStatus("created");
        Respon.setStatusCode(HttpStatus.CREATED.value());
        return ResponseEntity.status(Respon.getStatusCode()).body(Respon);
    }
    @GetMapping
    public ResponseEntity getAccountData() {
        List<AccountdataRespon> employees = accountdataServices.getAccountdata();
        return ResponseEntity.ok(employees);
    }
    @PutMapping("updated")
    public ResponseEntity updatedAccountData (@RequestBody CreateAccountdataDto createAccountdataDto ,@RequestParam("accountdataid") int accountdataId){

        // TODO ; TRY CACH BERFUNGSI UNTUK MELIHAT DATA DI DBMS KALAU ENGGAK ADA AKAN RETURN REPONSEN BAD REQUES
        try{
            String response = accountdataServices.updateAccountData(createAccountdataDto ,accountdataId);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("delete")
    public ResponseEntity deleteAccountData (@RequestBody CreateAccountdataDto createAccountdataDto,@RequestParam("delatedataid") int accountdataId){
       try {
           String response = accountdataServices.delateAccountData(createAccountdataDto ,accountdataId);
           return ResponseEntity.ok(response);
       } catch (Exception e) {
           return ResponseEntity.badRequest().body(e.getMessage());
       }
    }
}
