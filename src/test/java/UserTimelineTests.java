package test.java;

import main.java.Actions.UserTimelineActions;
import main.java.Response.UserTimeline.UserPosts;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UserTimelineTests {

    UserTimelineActions userTimelineActions = new UserTimelineActions();


    @Test
    public void checkNumberOfPosts() throws IOException {
        List<UserPosts> userPosts = userTimelineActions.getUserPosts();
        int numberOfPosts = userPosts.size();
        assertThat("Number of posts is not 20", numberOfPosts, equalTo(20));
    }
}