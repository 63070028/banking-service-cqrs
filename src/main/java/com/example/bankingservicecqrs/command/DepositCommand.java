package com.example.bankingservicecqrs.command;

public class DepositCommand extends BaseCommand<String>{
    public final double amount;

    public DepositCommand(String id, double amount) {
        super(id);
        this.amount = amount;
    }
}
