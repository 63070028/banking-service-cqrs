package com.example.bankingservicecqrs.command.dto;

import lombok.Data;

@Data
public class WithdrawRequest {
    private String id;
    private double amount;
}
