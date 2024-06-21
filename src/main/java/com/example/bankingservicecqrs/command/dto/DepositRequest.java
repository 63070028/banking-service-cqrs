package com.example.bankingservicecqrs.command.dto;

import lombok.Data;

@Data
public class DepositRequest {
    private String id;
    private double creditAmount;
}
