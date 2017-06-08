package main.java.Requests;

import main.java.Response.StatusUpdate.NewPost;
import main.java.Response.UserTimeline.UserPosts;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

public interface APIRequests {

    @Headers("screen_name: @andrewtechery")
    @POST("1.1/statuses/update.json")
    Call<NewPost> createNewTweet(@Query("status") String tweet);

    @Headers("screen_name: @andrewtechery")
    @POST("1.1/statuses/update.json")
    Call<NewPost> replyToTweet(@Query("status") String tweet,
                               @Query("in_reply_to_status_id") long replyID);

    @Headers("screen_name: @andrewtechery")
    @POST("1.1/statuses/update.json")
    Call<NewPost> createTweetWithLocation(@Query("status") String tweet,
                                          @Query("lat") double latitude,
                                          @Query("long") double longitude);

    @Headers("screen_name: @andrewtechery")
    @GET("1.1/statuses/user_timeline.json")
    Call<List<UserPosts>> getUserPosts();
}
//hello 2