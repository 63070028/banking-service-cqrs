package com.example.bankingservicecqrs.query.service;

import com.example.bankingservicecqrs.query.entity.AccountDetailsQueryEntity;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface AccountDetailsQueryService {
    public List<Object> listEventOfAccount(String id);

    public CompletableFuture<AccountDetailsQueryEntity> getAccountDetails(String id);
}



