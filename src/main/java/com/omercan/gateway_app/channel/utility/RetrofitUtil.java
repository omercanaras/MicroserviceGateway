package com.omercan.gateway_app.channel.utility;

import com.google.gson.JsonElement;
import com.omercan.gateway_app.utility.Util;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public final class RetrofitUtil
{
    private RetrofitUtil() {}

    public static <E> E callGenericBlock(Call<E> request)
    {
        try
        {
            Response<E> response = request.execute();

            if (!response.isSuccessful())
            {
                assert response.errorBody() != null;
                System.err.println("Unsuccessful response."
                        + "Response error: " + response.errorBody().string());

                return null;
            }

            return response.body();
        }
        catch (IOException e)
        {
            Util.showGeneralExceptionInfo(e);
            return null;
        }
        catch (RuntimeException e)
        {
            Util.showGeneralExceptionInfo(e);
            return null;
        }
    }
}
