package com.example.bankingservicecqrs.command.event;

public class DepositEvent extends BaseEvent<String>{
    public final double creditAmount;

    public DepositEvent(String id, double creditAmount) {
        super(id);
        this.creditAmount = creditAmount;
    }

}
