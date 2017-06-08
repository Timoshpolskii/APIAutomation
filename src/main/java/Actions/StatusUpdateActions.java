package main.java.Actions;

import main.java.Response.StatusUpdate.NewPost;
import retrofit2.Call;

import java.io.IOException;

import static main.java.Support.SingletonApiRequests.getApiRequests;

public class StatusUpdateActions {

    public NewPost createNewTweet(String tweet) throws IOException {
        Call<NewPost> call = getApiRequests().createNewTweet(tweet);

        NewPost body = null;
        try {
            body = call.execute().body();
        }
        catch (NullPointerException exception){
            System.out.println("Cannot get response body");
            exception.printStackTrace();
        }
        return body;
    }

    public  NewPost replyToTweet(String tweet, long replyID) throws IOException {
        Call<NewPost> call = getApiRequests().replyToTweet(tweet, replyID);

        NewPost body = null;
        try {
            body = call.execute().body();
        } catch (NullPointerException exception) {
            System.out.println("Cannot get response body");
            exception.printStackTrace();
        }
        return body;
    }

    public  NewPost createTweetWithLocation(String tweet, double latitude, double longitude) throws IOException {
        Call<NewPost> call = getApiRequests().createTweetWithLocation(tweet, latitude, longitude);

        NewPost body = null;
        try {
            body = call.execute().body();
        } catch (NullPointerException exception) {
            System.out.println("Cannot get response body");
            exception.printStackTrace();
        }
        return body;
    }
}