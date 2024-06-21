package com.example.bankingservicecqrs.command.aggregate;

import com.example.bankingservicecqrs.command.CreateAccountCommand;
import com.example.bankingservicecqrs.command.DepositCommand;
import com.example.bankingservicecqrs.command.WithdrawCommand;
import com.example.bankingservicecqrs.command.event.*;
import com.example.bankingservicecqrs.util.Status;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class BankAccountAggregate {
    @AggregateIdentifier
    private String id;
    private double balance;
    private Status status;
    private String errorMsg;;

    public BankAccountAggregate(){}

   @CommandHandler
   public BankAccountAggregate(CreateAccountCommand command){
       AggregateLifecycle.apply(new AccountCreatedEvent(command.id, command.accountBalance));
   }

   @EventSourcingHandler
   public void no(AccountCreatedEvent event){
        this.id = event.id;
        this.balance = event.balance;
        this.status = Status.CREATED;

        AggregateLifecycle.apply(new ActivateAccountEvent(this.id, Status.ACTIVATED));
   }

   @CommandHandler
   public void handle(DepositCommand command){
        if(this.balance + command.amount >= 0){
            AggregateLifecycle.apply(new ActivateAccountEvent(this.id, Status.ACTIVATED));
        }

        AggregateLifecycle.apply(new DepositEvent(command.id, command.amount));
   }

   @EventSourcingHandler
   public void on(DepositEvent event){
        this.balance += event.creditAmount;
   }

   @CommandHandler
   public void handle(WithdrawCommand command){
       if (this.status != Status.ACTIVATED){
           AggregateLifecycle.apply(new InactiveAccountEvent(this.id, "inactive account"));
           return;
       }

       if(this.balance - command.amount < 0){
           AggregateLifecycle.apply(new HoldAccountEvent(command.id, Status.HOLD, "insufficient balance"));
           return;
       }

       AggregateLifecycle.apply(new WithdrawEvent(command.id, command.amount));
   }

   @EventSourcingHandler
   public void on(WithdrawEvent event){
        this.balance -= event.debitAmount;
   }

    @EventSourcingHandler
    public void no(ActivateAccountEvent event){
        this.id = event.id;
        this.status = event.status;
    }

    @EventSourcingHandler
    protected void on(InactiveAccountEvent event){
        this.id = event.id;
        this.errorMsg = event.errorMsg;
    }

    @EventSourcingHandler
    protected void on(HoldAccountEvent event){
        this.id = event.id;
        this.status = event.status;
        this.errorMsg = event.errorMsg;
    }
}
