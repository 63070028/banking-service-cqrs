package com.example.bankingservicecqrs.command.event;

import com.example.bankingservicecqrs.util.Status;

public class ActivateAccountEvent extends BaseEvent<String>{
    public final Status status;

    public ActivateAccountEvent(String id, Status status) {
        super(id);
        this.status = status;
    }
}
