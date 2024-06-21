package com.example.bankingservicecqrs.command.dto;

import lombok.Data;

@Data
public class CreateAccountRequest {
    private double startingBalance;
}
