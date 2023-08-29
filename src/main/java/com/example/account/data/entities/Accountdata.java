package com.example.account.data.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Accountdata extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountdataid;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String email;
    @Column
    private String phoneNumber;

    @OneToOne(fetch = FetchType.LAZY)
    private Departement departement;

}

