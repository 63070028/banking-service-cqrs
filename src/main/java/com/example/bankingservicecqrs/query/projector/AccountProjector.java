package com.example.bankingservicecqrs.query.projector;

import com.example.bankingservicecqrs.command.aggregate.BankAccountAggregate;
import com.example.bankingservicecqrs.command.event.AccountCreatedEvent;
import com.example.bankingservicecqrs.command.event.ActivateAccountEvent;
import com.example.bankingservicecqrs.command.event.BaseEvent;
import com.example.bankingservicecqrs.query.entity.AccountDetailsQueryEntity;
import com.example.bankingservicecqrs.query.repository.AccountDetailsRepository;
import com.example.bankingservicecqrs.util.Status;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountProjector {
    @Autowired
    private AccountDetailsRepository accountDetailsRepository;

    @EventHandler
    public void on(AccountCreatedEvent event){
        accountDetailsRepository.save(new AccountDetailsQueryEntity(
                event.id,
                event.balance,
                Status.CREATED
        ));
    }

    @EventHandler
    public void on(ActivateAccountEvent event){
        Optional<AccountDetailsQueryEntity> entity = accountDetailsRepository.findById(event.id);
        if(entity.isPresent()){
            entity.get().setStatus(Status.ACTIVATED);
            accountDetailsRepository.save(entity.get());
        }
    }


}
