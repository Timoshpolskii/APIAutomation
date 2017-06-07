package main.java.Response.UserTimeline;

public class UserPosts {
    private String created_at;
    private long id;
    private User user;
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

    public String getCreated_at() {
        return created_at;
    }


    public long getId() {
        return id;
    }


    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "UserPosts{" +
                "created_at='" + created_at + '\'' +
                ", id=" + id +
                ", user=" + user.toString() +
                '}';
    }
}