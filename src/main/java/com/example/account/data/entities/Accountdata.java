package com.example.account.data.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
///TODO ANOTASI ENTITI WAJIB SETIAP KITA BUAT TABEL BARU SEBAGAI MODEL SEBUAH TABEL DI DBMS
// TODO SETIAP MENBUAT ID WAJIB ADA ANOTASI ID DAN GENERAETEDVALUE ---->IDENTITY===>>AUTO
// TODO ANOTASI LOMBOK SEBAGI KITA MEMUDAHKAN BUAT MELAKAKUKAN GET,SET,CONTSTURTOR,DLL...

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

