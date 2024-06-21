package com.example.bankingservicecqrs.command;

public class CreateAccountCommand extends BaseCommand<String>{
    public final double accountBalance;

    public CreateAccountCommand(String id, double accountBalance) {
        super(id);
        this.accountBalance = accountBalance;
    }
}
