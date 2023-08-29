package com.example.account.data.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data

public class Departement extends  BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int departementid;
    @Column
    private String departementName;
}
