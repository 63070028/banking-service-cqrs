package com.example.bankingservicecqrs.command.event;

public class InactiveAccountEvent extends BaseEvent<String>{
    public String errorMsg;

    public InactiveAccountEvent(String id, String errorMsg) {
        super(id);
        this.errorMsg = errorMsg;
    }
}
