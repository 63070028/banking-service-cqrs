package com.example.bankingservicecqrs.query;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class BaseQuery<T> {
    @TargetAggregateIdentifier
    public final T id;

    public BaseQuery(T id) {this.id = id;}
}
