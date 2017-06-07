package main.java.Requests;

import main.java.Response.StatusUpdate.NewPost;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface StatusUpdateRequest {

    @Headers("screen_name: @andrewtechery")
    @POST("1.1/statuses/update.json")
    Call<NewPost> updateStatus(@Query("status") String status);

    //    TODO: change naming to replyToTweet etc

    @Headers("screen_name: @andrewtechery")
    @POST("1.1/statuses/update.json")
    Call<NewPost> updateStatus(@Query("status") String status, @Query("in_reply_to_status_id") long id);

    @Headers("screen_name: @andrewtechery")
    @POST("1.1/statuses/update.json")
    Call<NewPost> updateStatus(@Query("status") String status, @Query("lat") double lat, @Query("long") double longitude);
}