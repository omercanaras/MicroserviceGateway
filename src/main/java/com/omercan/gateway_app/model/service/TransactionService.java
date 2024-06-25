package com.omercan.gateway_app.model.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.omercan.gateway_app.channel.utility.RetrofitUtil;
import com.omercan.gateway_app.security.model.UserPrincipal;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import java.util.List;

// Buradan devam edilecek.
@Service
public class TransactionService extends AbstractTransactionService
{
    @Override
    public List<JsonElement> findAllByUserID(Integer userID)
    {
        Call<List<JsonElement>> requestGetTransactionsOfUser = transactionCallable.getTransactionsOfUser(userID);

        return RetrofitUtil.callGenericBlock(requestGetTransactionsOfUser);
    }

    @Override
    public JsonElement createTransactionData(JsonElement savedProduct, UserPrincipal user) {

        JsonObject transactionData = new JsonObject();

        transactionData.addProperty("userId" ,user.getId());
        transactionData.addProperty("productId" , savedProduct.getAsJsonObject().get("productId").getAsInt() );

        return transactionData;
    }

    @Override
    public void deleteById(Integer id)
    {
        Call<String> requestSavedEntity = transactionCallable.deleteByID(id);

        RetrofitUtil.callGenericBlock(requestSavedEntity);
    }

    @Override
    public JsonElement findById(Integer id)
    {
        Call<JsonElement> requestSavedEntity = transactionCallable.findById(id);

        return RetrofitUtil.callGenericBlock(requestSavedEntity);
    }

    @Override
    public JsonElement save(JsonElement entity)
    {
        Call<JsonElement> requestSavedEntity = transactionCallable.save(entity);

        return RetrofitUtil.callGenericBlock(requestSavedEntity);
    }

    @Override
    public List<JsonElement> getAll()
    {
        Call<List<JsonElement>> requestGetAll = transactionCallable.getAll();

        return RetrofitUtil.callGenericBlock(requestGetAll);
    }
}
