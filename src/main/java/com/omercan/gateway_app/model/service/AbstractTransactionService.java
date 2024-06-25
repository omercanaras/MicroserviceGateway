package com.omercan.gateway_app.model.service;

import com.google.gson.JsonElement;
import com.omercan.gateway_app.channel.repository.TransactionCallable;
import com.omercan.gateway_app.security.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractTransactionService implements EntityService<JsonElement, Integer>
{
    @Autowired
    protected TransactionCallable transactionCallable;

    public abstract List<JsonElement> findAllByUserID(Integer userID);

    public abstract JsonElement createTransactionData(JsonElement savedProduct, UserPrincipal user);
}
