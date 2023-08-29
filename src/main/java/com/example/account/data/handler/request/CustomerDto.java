package com.example.account.data.handler.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private String name;
    private String alamat;
    private String phone;
    private String noRekening;
    private int saldo;
}
