package com.example.bankingservicecqrs.query.repository;

import com.example.bankingservicecqrs.query.entity.AccountDetailsQueryEntity;
import org.springframework.data.repository.CrudRepository;

public interface AccountDetailsRepository extends CrudRepository<AccountDetailsQueryEntity, String> {
}
