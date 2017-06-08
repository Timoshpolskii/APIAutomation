package main.java.Actions;

import main.java.Response.UserTimeline.UserPosts;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

import static main.java.Support.SingletonApiRequests.getApiRequests;

public class UserTimelineActions {

    public List<UserPosts> getUserPosts() throws IOException {

        Call<List<UserPosts>> call = getApiRequests().getTimeline();
        List<UserPosts> body = null;
        try {
            body = call.execute().body();
        }
        catch (NullPointerException exception){
            System.out.println("Cannot get response body");
            exception.printStackTrace();
        }
        return body;
    }
}