package com.example.account.data.services;

import com.example.account.data.entities.Departement;
import com.example.account.data.handler.request.CreatedDepartementDto;
import com.example.account.data.handler.request.CreatedListDepartementDto;
import com.example.account.data.repository.Departementrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
///TODO PACKAGE SERVICE SEBAGI BUSSNIS LOGIC
@Service
public class DepartementService {
    @Autowired
    private Departementrepository departementrepo;

    //TODO ; MENCEK NULL DATA DI REPOSITORI DARI DBMS DEPARTEMENT REPO..
    // TODO ; MENSAVE DATA BARU DI DAPARTEMEN REPO....
    public String createDepartement(CreatedDepartementDto createdDepartementDto)
        throws Exception {
        Departement departement = new Departement();
        if (departementrepo.findDepartementBydepartementName(createdDepartementDto.getDepartementName()) == null ){
            departement.setDepartementName(createdDepartementDto.getDepartementName());
            departement.setCreatedDate(new Date());
            departement.setUpdatedDate(new Date());

        }else {
           throw new Exception(" Departement Already exist , Please give unigue name departement");
        }
        departementrepo.save(departement);
        return "Departemen Created successfully";
    }

    //TODO ; METODE DIMANA MENGGUNAKAN ARRAY LIST DAPAT MEMASUKAN BANYAK DATA KE DBMS SECARA BANNYAK ATAU BERURUTAN....BODY
    // TODO ; Melalui rest API Reques BOdy
    @Transactional
    public String createdDepartementList(CreatedListDepartementDto createdListDepartementDto){
        List<Departement> departements = new ArrayList<>();
       for(CreatedDepartementDto createdDepartementDto : createdListDepartementDto.getDepartementListName()) {
           Departement departement = new Departement();
           departement.setDepartementName(createdDepartementDto.getDepartementName());
           departement.setUpdatedDate(new Date());
           departement.setCreatedDate(new Date());
           departements.add(departement);
       }
       departementrepo.saveAll(departements);
       return "departemnd succesfully saved";
    }
}
