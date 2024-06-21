package com.example.bankingservicecqrs.command.event;

public class AccountCreatedEvent extends BaseEvent<String>{
    public final double balance;

    public AccountCreatedEvent(String id, double balance) {
        super(id);
        this.balance = balance;
    }

}
