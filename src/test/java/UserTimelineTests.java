package test.java;

import main.java.Actions.ApiActions;
import main.java.Response.UserTimeline.UserTweets;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static main.java.Support.SingletonApiRequests.getApiRequests;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UserTimelineTests {
    ApiActions apiActions = new ApiActions();


    @Test
    public void checkNumberOfPosts() throws IOException {
        List<UserTweets> userTweets = (List<UserTweets>) apiActions.execute(getApiRequests().getUserTweets());
        int numberOfPosts = userTweets.size();
        assertThat("Number of posts is not 20", numberOfPosts, equalTo(20));
    }
}