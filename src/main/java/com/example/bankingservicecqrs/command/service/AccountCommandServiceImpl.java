package com.example.bankingservicecqrs.command.service;

import com.example.bankingservicecqrs.command.CreateAccountCommand;
import com.example.bankingservicecqrs.command.DepositCommand;
import com.example.bankingservicecqrs.command.WithdrawCommand;
import com.example.bankingservicecqrs.command.dto.CreateAccountRequest;
import com.example.bankingservicecqrs.command.dto.DepositRequest;
import com.example.bankingservicecqrs.command.dto.WithdrawRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
@Service
public class AccountCommandServiceImpl implements AccountCommandService{

    private final CommandGateway commandGateway;

    public AccountCommandServiceImpl(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<String> createAccount(CreateAccountRequest request) {
        return commandGateway.send(new CreateAccountCommand(
                UUID.randomUUID().toString(),
                request.getStartingBalance()
        ));
    }

    @Override
    public CompletableFuture<String> depositMoney(DepositRequest request) {
        return commandGateway.send(new DepositCommand(
                request.getId(),
                request.getCreditAmount()
        ));
    }

    @Override
    public CompletableFuture<String> withdrawMoney(WithdrawRequest request) {
        return commandGateway.send(new WithdrawCommand(
                request.getId(),
                request.getAmount()
        ));
    }
}
