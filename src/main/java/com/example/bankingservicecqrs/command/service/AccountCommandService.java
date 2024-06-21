package com.example.bankingservicecqrs.command.service;

import com.example.bankingservicecqrs.command.dto.CreateAccountRequest;
import com.example.bankingservicecqrs.command.dto.DepositRequest;
import com.example.bankingservicecqrs.command.dto.WithdrawRequest;

import java.util.concurrent.CompletableFuture;

public interface AccountCommandService {
    public CompletableFuture<String> createAccount(CreateAccountRequest request);

    public CompletableFuture<String> depositMoney(DepositRequest request);

    public CompletableFuture<String> withdrawMoney(WithdrawRequest request);
}
