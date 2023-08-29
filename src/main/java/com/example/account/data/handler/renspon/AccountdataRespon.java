package com.example.account.data.handler.renspon;

import lombok.Data;

import javax.persistence.Column;

@Data
public class AccountdataRespon {
    private int accountdataid;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String email;
    @Column
    private String phoneNumber;
    private DepartementDto departementDto;
    private  long totalData;
    private int totalHalamant;

}
