package main.java.Actions;

import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class ApiActions<T> {

    public T execute(Call<T> call) throws IOException {
        Response<T> response = call.execute();
        if (response.code() != 200) {
            System.out.println("Cannot get response body. Response code is " + response.code() +
                    " " + response.errorBody().string());
        }
        return response.body();
    }

    public void getRequestParameters(Call<T> call) throws IOException {
        String headers = call.request().headers().toString();
        String url = call.request().url().toString();

        System.out.println(headers);
        System.out.println(url);
    }
}