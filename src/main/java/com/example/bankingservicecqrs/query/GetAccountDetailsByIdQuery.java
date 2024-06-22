package com.example.bankingservicecqrs.query;

public class GetAccountDetailsByIdQuery extends BaseQuery<String>{
    public GetAccountDetailsByIdQuery(String id) {
        super(id);
    }
}
