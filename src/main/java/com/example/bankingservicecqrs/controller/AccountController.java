package com.example.bankingservicecqrs.controller;

import com.example.bankingservicecqrs.command.dto.CreateAccountRequest;
import com.example.bankingservicecqrs.command.dto.DepositRequest;
import com.example.bankingservicecqrs.command.dto.WithdrawRequest;
import com.example.bankingservicecqrs.command.service.AccountCommandServiceImpl;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController(value = "/account")
@ApiResponse(description = "Banking Account Command Related Endpoints")
public class AccountController {

    @Autowired
    private AccountCommandServiceImpl accountCommandService;

    @PostMapping("/create")
    public CompletableFuture<String> CreateAccount(@RequestBody CreateAccountRequest request){
        return accountCommandService.createAccount(request);
    }

    @PostMapping("/deposit")
    public CompletableFuture<String> CreateAccount(@RequestBody DepositRequest request){
        return accountCommandService.depositMoney(request);
    }

    @PostMapping("/withdraw")
    public CompletableFuture<String> CreateAccount(@RequestBody WithdrawRequest request){
        return accountCommandService.withdrawMoney(request);
    }

}
