package main.java.Actions;

import retrofit2.Call;

import java.io.IOException;

public class ApiActions<T> {

    public T execute(Call<T> call) throws IOException {
        T body = null;
        try {
            body = call.execute().body();
        } catch (NullPointerException exception) {
            System.out.println("Cannot get response body");
            exception.printStackTrace();
        }
        return body;
    }
}