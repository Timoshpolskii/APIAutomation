package main.java.Support;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

public class SingletonRetrofit {

    private static Retrofit retrofit = null;

    private SingletonRetrofit() {
    }

    public static synchronized Retrofit getRetrofit() {

        if (retrofit == null) {

            OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer("z7tNU6PYcciYUrnsXXXRapDqM",
                    "zS40mN5Xw24uu69A3l0EY5XXQ5PIat03byZqDfJCcc5SxXlLBt");
            consumer.setTokenWithSecret("3390666947-cfbg4q7A13ip5tPqtXBd6bZ48FWsSzpgPgrtzee",
                    "qvL3gk3r8OlSjm9J4rCeaBny9GNvSSqJOscz1mcc6fs4u");

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new SigningInterceptor(consumer))
                    .build();

            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.twitter.com/")
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}