package main.java.Requests;

import main.java.Response.UserTimeline.UserPosts;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

import java.util.List;


public interface UserTimelineRequest {

    //    TODO: move this request to another class

    @Headers("screen_name: @andrewtechery")
    @GET("1.1/statuses/user_timeline.json")
    Call<List<UserPosts>> getTimeline();
}