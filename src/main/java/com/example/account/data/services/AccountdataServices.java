 package com.example.account.data.services;

import com.example.account.data.entities.Accountdata;
import com.example.account.data.entities.Departement;
import com.example.account.data.handler.renspon.AccountdataRespon;
import com.example.account.data.handler.request.DepartementDto;
import com.example.account.data.handler.request.CreateAccountdataDto;
import com.example.account.data.repository.Accountdatarepository;
import com.example.account.data.repository.Departementrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

    @Service //TODO : ANOTASI SERVICE INI PENTING MENGHUNGKAN REPO KE CLASS SERVICE WAJIB DI GUNAKAN SAAT PEMANGGILAN CLASS SERVICE KE REPO...
    public class AccountdataServices {
     @Autowired
     private Accountdatarepository accountdatarepo;
     @Autowired
     Departementrepository departementrepo;
    @Transactional
    // TODO ; ANOTASI TRANSACTIONAL --IALAH ANOTASI "KETIKA DATA ACCOUNDATA YANG SALAH
    // TAPI DATA DEPERTEMENT BENAR DIAAKAN AKAAN OTOMATIS KE SAVE DATA DEPERTEMENYA
    // TODO : ADA JUGA ANOTASI MODIFYING IALAH ANOTASI DIMANA KITA  MENGDELETE DATA JOIN
     public String CreatedAccountdata(CreateAccountdataDto createAccountdataDTO) {
         Accountdata accountdata = new Accountdata();
         accountdata.setName(createAccountdataDTO.getName());
         accountdata.setAddress(createAccountdataDTO.getAddress());
         accountdata.setEmail(createAccountdataDTO.getEmail());
         accountdata.setPhoneNumber(createAccountdataDTO.getPhoneNumber());
         accountdata.setCreatedDate(new Date());
         accountdata.setUpdatedDate(new Date());
         accountdatarepo.save(accountdata);
         return "account data sudah berhasih di tambahkan";

     }
    @Transactional
     public List<AccountdataRespon> getAccountdata() {
         List<Accountdata> accountData = accountdatarepo.findAll();
         //TODO : PEMANGGILAN PAGE ---> DI REPOSITORY ACCOUNTDATA

         Pageable pageable = PageRequest.of(0, 10);
         Page<Accountdata> page = accountdatarepo.findAll(pageable);

         // TODO ; PERBEDAA EAGER DAN LAZY (WAJIB DIKETAHUI...)
         // TODO : EGER METODE INI MENGANGAMBIL SEMUA LIST SEMUA DATA YANG ADA DI DBMS KITA.
         // TODO : METODE LAZY HARUS MEMAKAI BISNISS LOGIC UNTUK GET DATA YANG INGIN DI PAKAI, YAITU MEMAKAI SISTEM LOOPING;

        //TODO : SERVICE CREATED DATA KE REST API MENGGUNAKAN BODY
         List<AccountdataRespon> response = new ArrayList<>();
         for (Accountdata accountDataSet : page) {
             AccountdataRespon accountdataRespon = new AccountdataRespon();
             accountdataRespon.setAccountdataid(accountDataSet.getAccountdataid());
             accountdataRespon.setName(accountDataSet.getName());
             accountdataRespon.setEmail(accountDataSet.getEmail());
             accountdataRespon.setAddress(accountDataSet.getAddress());
             accountdataRespon.setPhoneNumber(accountDataSet.getPhoneNumber());
             Departement departement = accountDataSet.getDepartement();
             DepartementDto departementDto = new DepartementDto();
             departementDto.setDepartementName(departementDto.getDepartementName());
             accountdataRespon.setDepartementDto(departementDto);
             accountdataRespon.setTotalData(page.getTotalElements());
             accountdataRespon.setTotalHalamant(page.getTotalPages());
             response.add(accountdataRespon);

         }
         return response;
     }
     @Transactional
     // TODO : SERVICE BUAT UPDATED ACCUOUNT DATA;
     public String updateAccountData(CreateAccountdataDto createAccountDataDto, int accountdataId)
         throws Exception {
         Optional<Accountdata> accountdata = accountdatarepo.findById(accountdataId);
         if (!accountdata.isPresent()) {
             throw new Exception("AccountData"+accountdataId+"Not Found plaase Cek DBMS");
         }
         accountdata.get().setName(createAccountDataDto.getName());
         accountdata.get().setEmail(createAccountDataDto.getEmail());
         accountdata.get().setPhoneNumber(createAccountDataDto.getPhoneNumber());
         accountdata.get().setAddress(createAccountDataDto.getAddress());
         Optional<Departement> departement = departementrepo.findById(accountdataId);
         departement.get().setDepartementid(createAccountDataDto.getDepartementid());
         accountdatarepo.save(accountdata.get());
         return "Account Data Update";
     }

     //TODO : SERVICE BUAT DELETE
     @Transactional
     public String delateAccountData(CreateAccountdataDto createAccountDataDto, int accountdataId)
         throws Exception {
         Optional<Accountdata> accountdata = accountdatarepo.findById(accountdataId);
         if (!accountdata.isPresent()) {
             throw new Exception("AccountData "+accountdata+" Not Found by ID");
         }
         accountdatarepo.delete(accountdata.get());
         return "Account berhasil Terhapus";
     }
 }
