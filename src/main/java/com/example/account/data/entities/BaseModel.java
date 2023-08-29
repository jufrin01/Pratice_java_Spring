package com.example.account.data.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;
///TODO CLASS SUPER DIMANA SUPER CLASS INI MEGENERATED KE KESEMUA TABEL MELALUI SISTEM OOP/TURUNAN =(INHERINTACE,DLL)
@Data
@MappedSuperclass
public class BaseModel {
    @Column
    private Date createdDate;
    @Column
    private Date updatedDate;
}
