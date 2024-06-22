package com.example.bankingservicecqrs.query.projector;

import com.example.bankingservicecqrs.command.DepositCommand;
import com.example.bankingservicecqrs.command.event.*;
import com.example.bankingservicecqrs.query.GetAccountDetailsByIdQuery;
import com.example.bankingservicecqrs.query.entity.AccountDetailsQueryEntity;
import com.example.bankingservicecqrs.query.repository.AccountDetailsRepository;
import com.example.bankingservicecqrs.util.Status;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
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
                Status.CREATED.getValue()
        ));
    }

    @EventHandler
    public void on(ActivateAccountEvent event){
        Optional<AccountDetailsQueryEntity> entity = accountDetailsRepository.findById(event.id);
        if(entity.isPresent()){
            entity.get().setStatus(Status.ACTIVATED.getValue());
            accountDetailsRepository.save(entity.get());
        }
    }

    @EventHandler
    public void on(DepositEvent event){
        Optional<AccountDetailsQueryEntity> entity = accountDetailsRepository.findById(event.id);
        if(entity.isPresent()){
            entity.get().setBalance(entity.get().getBalance() + event.creditAmount);
            accountDetailsRepository.save(entity.get());
        }
    }

    @EventHandler
    public void on(WithdrawEvent event){
        Optional<AccountDetailsQueryEntity> entity = accountDetailsRepository.findById(event.id);
        if(entity.isPresent()){
            entity.get().setBalance(entity.get().getBalance() - event.debitAmount);
            accountDetailsRepository.save(entity.get());
        }
    }

    @EventHandler
    public void on(HoldAccountEvent event){
        Optional<AccountDetailsQueryEntity> entity = accountDetailsRepository.findById(event.id);
        if(entity.isPresent()){
            entity.get().setStatus(Status.HOLD.getValue());
            accountDetailsRepository.save(entity.get());
        }
    }

    @QueryHandler
    public AccountDetailsQueryEntity on(GetAccountDetailsByIdQuery query){
        Optional<AccountDetailsQueryEntity> entity = accountDetailsRepository.findById(query.id);
        return entity.orElse(null);
    }

}
