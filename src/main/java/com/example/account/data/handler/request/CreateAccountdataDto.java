package com.example.account.data.handler.request;

import lombok.Data;
/// TODO ADRIBUT ACCOUNDATA
@Data
public class CreateAccountdataDto {
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    private int departementid;

}
