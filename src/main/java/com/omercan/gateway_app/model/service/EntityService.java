package com.omercan.gateway_app.model.service;

import java.util.List;

public interface EntityService<E , ID>
{
    void deleteById(ID id);

    E findById(ID id);

    E save(E entity);

    List<E> getAll();
}
