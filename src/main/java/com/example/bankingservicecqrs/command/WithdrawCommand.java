package com.example.bankingservicecqrs.command;

public class WithdrawCommand extends BaseCommand<String>{
    public final double amount;

    public WithdrawCommand(String id, double amount) {
        super(id);
        this.amount = amount;
    }
}
