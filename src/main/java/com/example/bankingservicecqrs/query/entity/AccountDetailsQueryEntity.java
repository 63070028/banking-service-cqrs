package com.example.bankingservicecqrs.query.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class AccountDetailsQueryEntity {

    public AccountDetailsQueryEntity() {}
    public AccountDetailsQueryEntity(String id, double balance, String status) {
        this.id = id;
        this.balance = balance;
        this.status = status;
    }

    @Id
    private String id;

    private double balance;

    private String status;

}
