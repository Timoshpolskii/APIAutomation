package main.java.Response.UserTimeline;

import com.google.gson.annotations.SerializedName;

public class UserPosts {

    private String text;

    @SerializedName("in_reply_to_status_id")
    private long inReplyToStatusId;

    private Place place;

    public Place getPlace() {
        return place;
    }

    public long getInReplyToStatusId() {
        return inReplyToStatusId;
    }

    public String getText() {
        return text;
    }

}