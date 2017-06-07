package main.java.Actions;

import main.java.Response.StatusUpdate.NewPost;
import retrofit2.Call;

import java.io.IOException;

import static main.java.Support.SingletonApiRequests.getApiRequests;

public class StatusUpdateActions {

    //    TODO: maybe now we can user generics? push to git before try to do this

    public NewPost postStatus(String status) throws IOException {
        Call<NewPost> call = getApiRequests().updateStatus(status);

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
        Call<NewPost> call = getApiRequests().updateStatus(status, id);

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
        Call<NewPost> call = getApiRequests().updateStatus(status, lat, longitude);

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