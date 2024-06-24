package com.omercan.gateway_app.model.service;

import com.google.gson.JsonElement;
import com.omercan.gateway_app.channel.repository.ProductCallable;
import org.springframework.beans.factory.annotation.Autowired;

// ******12
public abstract class AbstractProductService implements EntityService<JsonElement, Integer>
{
    @Autowired
    protected ProductCallable productCallable;


}
