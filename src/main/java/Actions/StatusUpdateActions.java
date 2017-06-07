package main.java.Actions;

import main.java.Response.StatusUpdate.NewPost;
import retrofit2.Call;

import java.io.IOException;

import static main.java.Support.SingletonApiRequests.getApiRequests;

public class StatusUpdateActions {

    //    TODO: maybe now we can user generics? push to git before try to do this

    public NewPost postStatus(String status) throws IOException {
        Call<NewPost> call = getApiRequests().createNewTweet(status);

        NewPost body = null;
        try {
            body = call.clone().execute().body();
        }
        catch (NullPointerException exception){
            System.out.println("Cannot get response body");
            exception.printStackTrace();
        }
        return body;
    }

    public  NewPost postStatus(String status, long id) throws IOException {
        Call<NewPost> call = getApiRequests().replyToTweet(status, id);

        NewPost body = null;
        try {
            body = call.clone().execute().body();
        } catch (NullPointerException exception) {
            System.out.println("Cannot get response body");
            exception.printStackTrace();
        }
        return body;
    }

    public  NewPost postStatus(String status, double lat, double longitude) throws IOException {
        Call<NewPost> call = getApiRequests().createTweetWithLocation(status, lat, longitude);

        NewPost body = null;
        try {
            body = call.clone().execute().body();
        } catch (NullPointerException exception) {
            System.out.println("Cannot get response body");
            exception.printStackTrace();
        }
        return body;
    }
}