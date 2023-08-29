package com.example.account.data.controller;

import com.example.account.data.handler.request.CreatedDepartementDto;
import com.example.account.data.handler.request.CreatedListDepartementDto;
import com.example.account.data.services.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api-v1/departement")
public class DepartementConttroller {
    @Autowired
    private DepartementService departementService;
    @PostMapping("created")
    public ResponseEntity createdDepartement(@RequestBody CreatedDepartementDto createdDepartementDto){
        try {
            String response = departementService.createDepartement(createdDepartementDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    ///todo PATH GAK BOLEH SAMA SOAL NYA POSTMAPING SAMA
    ///KALAU BEDA POST KE  GETT MAPPING BISA KITA MASUKAN PATH YANG SAMA
    @PostMapping("created-list")
    public ResponseEntity createdDepartementList(@RequestBody CreatedListDepartementDto createdListDepartementDto) {
        try {
            String response = departementService.createdDepartementList(createdListDepartementDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
//TODO PENGETIKAN DI BODY DI REST API MENGGUNAKAN ARRAY
//{
//    "departementListName" :[
//    {
//    "departementName" : "SDM"
//    },
//    {
//    "departementName" : "DIVISI DELOPMENT"
//    },
//    {
//    "departementName" : "DESIGN GRAFIS"
//    },
//    {
//    "departementName" : "GAME TESTER"
//    }
//    ]
//    }



