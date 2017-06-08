package test.java;

import main.java.Actions.ApiActions;
import main.java.Response.UserTimeline.UserPosts;
import org.testng.annotations.Test;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

import static main.java.Support.SingletonApiRequests.getApiRequests;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UserTimelineTests {
    ApiActions apiActions = new ApiActions();


    @Test
    public void checkNumberOfPosts() throws IOException {
        Call<List<UserPosts>> userPostsCall = getApiRequests().getUserPosts();
        List<UserPosts> userPosts = (List<UserPosts>) apiActions.execute(userPostsCall);
        int numberOfPosts = userPosts.size();
        assertThat("Number of posts is not 20", numberOfPosts, equalTo(20));
    }
}