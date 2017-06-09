package main.java.Actions;

import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class ApiActions<T> {

    public T execute(Call<T> call) throws IOException {
        T response = call.execute().body();
        try {
            response.toString();
        } catch (NullPointerException exception) {
            System.out.println("Cannot get response body");
        }
        return response;
    }

    public void getRequestParameters(Call<T> call) throws IOException {
        String headers = call.request().headers().toString();
        String url = call.request().url().toString();

        System.out.println(headers);
        System.out.println(url);
    }
}