package com.example.bankingservicecqrs.query.service;

import com.example.bankingservicecqrs.query.GetAccountDetailsByIdQuery;
import com.example.bankingservicecqrs.query.entity.AccountDetailsQueryEntity;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class AccountDetailsQueryServiceImpl implements AccountDetailsQueryService {
    private final EventStore eventStore;
    private final QueryGateway queryGateway;

    public AccountDetailsQueryServiceImpl(EventStore eventStore, QueryGateway queryGateway) {
        this.eventStore = eventStore;
        this.queryGateway = queryGateway;
    }

    @Override
    public List<Object> listEventOfAccount(String id) {
        return eventStore.readEvents(id).asStream().map(Message::getPayload).collect(Collectors.toList());
    }
    @Override
    public CompletableFuture<AccountDetailsQueryEntity> getAccountDetails (String id){
        return queryGateway.query(new GetAccountDetailsByIdQuery(id), AccountDetailsQueryEntity.class);
    }
}
