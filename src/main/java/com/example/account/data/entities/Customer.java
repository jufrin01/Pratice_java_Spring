package com.example.account.data.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "alamat")
    private String alamat;
    @Column(name = "phone")
    private String phone;
    @Column(name = "no_rekening")
    private String noRekening;
    @Column(name = "saldo")
    private int saldo;
}
