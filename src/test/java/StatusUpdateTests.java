package test.java;

import main.java.Actions.ApiActions;
import main.java.Response.StatusUpdate.NewPost;
import main.java.Response.UserTimeline.UserPosts;
import org.testng.annotations.Test;
import retrofit2.Call;

import static main.java.Support.SingletonApiRequests.getApiRequests;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.List;

public class StatusUpdateTests {
    ApiActions apiActions = new ApiActions();


    @Test
    public void checkRussianLanguage() throws IOException {
        Call<NewPost> newPostCall = getApiRequests().createNewTweet("привет");
        NewPost newPost = (NewPost) apiActions.execute(newPostCall);
        String currentLanguage = newPost.getLang();
        assertThat("Language is not Russian", currentLanguage, equalTo("ru"));
    }

    @Test
    public void checkEnglishLanguage() throws IOException {
        Call<NewPost> newPostCall = getApiRequests().createNewTweet("hello");
        NewPost newPost = (NewPost) apiActions.execute(newPostCall);
        String currentLanguage = newPost.getLang();
        assertThat("Language is not English", currentLanguage, equalTo("en"));
    }

    @Test
    public void checkTextOfNewPost() throws IOException {
        String text = "some text";

        Call<NewPost> newPostCall = getApiRequests().createNewTweet(text);
        apiActions.execute(newPostCall);

        Call<List<UserPosts>> userPostsCall = getApiRequests().getUserPosts();
        List<UserPosts> userPosts = (List<UserPosts>) apiActions.execute(userPostsCall);

        String textFromResponse = userPosts.get(0).getText();
        assertThat("Text from response differs from request.", textFromResponse, equalTo(text));
    }

    @Test
    public void checkIDsAfterReply() throws IOException {
        Call<NewPost> newPostCall = getApiRequests().createNewTweet("test1");
        NewPost firstPost = (NewPost) apiActions.execute(newPostCall);
        long idOfFirstPost = firstPost.getId();

        Call<NewPost> replyToPost = getApiRequests().replyToTweet("test2", idOfFirstPost);
        apiActions.execute(replyToPost);

        Call<List<UserPosts>> userPostsCall = getApiRequests().getUserPosts();
        List<UserPosts> userPosts = (List<UserPosts>) apiActions.execute(userPostsCall);

        long actualReplyId = userPosts.get(0).getInReplyToStatusId();

        assertThat("ID's are differed", idOfFirstPost, equalTo(actualReplyId));
    }

    @Test
    public void checkPlaceWithCoordinates() throws IOException {
        Call<NewPost> newPostCall = getApiRequests().createTweetWithLocation("test3", 37.7821120598956, -122.400612831116);
        apiActions.execute(newPostCall);

        Call<List<UserPosts>> userPostsCall = getApiRequests().getUserPosts();
        List<UserPosts> userPosts = (List<UserPosts>) apiActions.execute(userPostsCall);

        String actualCountry = userPosts.get(0).getPlace().getCountry();
        assertThat("Country is wrong", actualCountry, equalTo("United States"));

        String actualCityName = userPosts.get(0).getPlace().getFullName();
        assertThat("Name of city is wrong", actualCityName, equalTo("San Francisco, CA"));

        String actualCountryCode = userPosts.get(0).getPlace().getCountryCode();
        assertThat("Country code is wrong", actualCountryCode, equalTo("US"));

    }
}