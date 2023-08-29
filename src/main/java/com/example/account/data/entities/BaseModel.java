package com.example.account.data.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseModel {
    @Column
    private Date createdDate;
    @Column
    private Date updatedDate;
}
