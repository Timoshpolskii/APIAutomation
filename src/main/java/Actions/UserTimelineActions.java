package main.java.Actions;

import main.java.Response.UserTimeline.UserPosts;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

import static main.java.Support.SingletonApiRequests.getApiRequests;

public class UserTimelineActions {

//    TODO: compare this class with StatusUpdateActions
    private Call<List<UserPosts>> call = getApiRequests().getTimeline();

    public List<UserPosts> getUserInfo() throws IOException {

        List<UserPosts> body = null;
        try {
            body = call.clone().execute().body();
        }
        catch (NullPointerException exception){
            System.out.println("Cannot get response body");
            exception.printStackTrace();
        }
        return body;
    }
}