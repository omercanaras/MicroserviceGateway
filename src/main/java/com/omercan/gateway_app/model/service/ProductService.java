package com.omercan.gateway_app.model.service;

import com.google.gson.JsonElement;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import java.util.List;

import com.omercan.gateway_app.channel.utility.RetrofitUtil;

@Service
public class ProductService extends AbstractProductService
{
    @Override
    public void deleteById(Integer id)
    {
        Call<String> requestDeletedEntity = productCallable.deleteByID(id);

        RetrofitUtil.callGenericBlock(requestDeletedEntity);
    }

    @Override
    public JsonElement findById(Integer id)
    {
        Call<JsonElement> requestFoundEntity = productCallable.findByID(id);

        return RetrofitUtil.callGenericBlock(requestFoundEntity);
    }

    @Override
    public JsonElement save(JsonElement entity)
    {
        Call<JsonElement> requestSavedEntity = productCallable.save(entity);

        return RetrofitUtil.callGenericBlock(requestSavedEntity);
    }

    @Override
    public List<JsonElement> getAll()
    {
        Call<List<JsonElement>> requestGetAll = productCallable.getAll();

        return RetrofitUtil.callGenericBlock(requestGetAll);
    }
}
