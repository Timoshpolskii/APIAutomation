package main.java.Response.UserTimeline;

public class UserPosts {

    private String text;
    private long in_reply_to_status_id;
    private Place place;

    public Place getPlace() {
        return place;
    }

    public long getIn_reply_to_status_id() {
        return in_reply_to_status_id;
    }

    public String getText() {
        return text;
    }

}