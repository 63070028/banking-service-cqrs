package com.example.bankingservicecqrs.command.event;

public class WithdrawEvent extends BaseEvent<String>{
    public final double debitAmount;

    public WithdrawEvent(String id, double debitAmount) {
        super(id);
        this.debitAmount = debitAmount;
    }
}
