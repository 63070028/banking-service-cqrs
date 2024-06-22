package com.example.bankingservicecqrs.controller;

import com.example.bankingservicecqrs.command.dto.CreateAccountRequest;
import com.example.bankingservicecqrs.command.dto.DepositRequest;
import com.example.bankingservicecqrs.command.dto.WithdrawRequest;
import com.example.bankingservicecqrs.command.service.AccountCommandServiceImpl;
import com.example.bankingservicecqrs.query.entity.AccountDetailsQueryEntity;
import com.example.bankingservicecqrs.query.service.AccountDetailsQueryService;
import com.example.bankingservicecqrs.query.service.AccountDetailsQueryServiceImpl;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController(value = "/account")
@ApiResponse(description = "Banking Account Endpoints")
public class AccountController {

    @Autowired
    private AccountCommandServiceImpl accountCommandService;
    @Autowired
    private AccountDetailsQueryServiceImpl accountDetailsQueryService;

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

    @GetMapping("/events")
    public List<Object> getEvents(@RequestParam String id){
        return accountDetailsQueryService.listEventOfAccount(id);
    }

    @GetMapping("/details")
    public CompletableFuture<AccountDetailsQueryEntity> getDetails(@RequestParam String id){
        return accountDetailsQueryService.getAccountDetails(id);
    }

}
