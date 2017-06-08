package test.java;

import main.java.Actions.StatusUpdateActions;
import main.java.Actions.UserTimelineActions;
import main.java.Response.StatusUpdate.NewPost;
import main.java.Response.UserTimeline.UserPosts;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.List;

public class StatusUpdateTests {
    StatusUpdateActions statusUpdateActions = new StatusUpdateActions();
    UserTimelineActions userTimelineActions = new UserTimelineActions();

    @Test
    public void checkRussianLanguage() throws IOException {
        NewPost newPost = statusUpdateActions.createNewTweet("привет");
        String currentLanguage = newPost.getLang();
        assertThat("Language is not Russian", currentLanguage, equalTo("ru"));
    }

    @Test
    public void checkEnglishLanguage() throws IOException {
        NewPost newPost = statusUpdateActions.createNewTweet("hello");
        String currentLanguage = newPost.getLang();
        assertThat("Language is not English", currentLanguage, equalTo("en"));
    }

    @Test
    public void checkTextOfNewPost() throws IOException {
        String text = "some text";

        statusUpdateActions.createNewTweet(text);
        List<UserPosts> userPosts = userTimelineActions.getUserPosts();

        String textFromResponse = userPosts.get(0).getText();
        assertThat("Text from response differs from request.", textFromResponse, equalTo(text));
    }

    @Test
    public void checkIDsAfterReply() throws IOException {
        NewPost firstPost = statusUpdateActions.createNewTweet("test1");
        long idOfFirstPost = firstPost.getId();

        statusUpdateActions.replyToTweet("test2", idOfFirstPost);

        List<UserPosts> userPosts = userTimelineActions.getUserPosts();
        long actualReplyId = userPosts.get(0).getInReplyToStatusId();

        assertThat("ID's are differed", idOfFirstPost, equalTo(actualReplyId));
    }

    @Test
    public void checkPlaceWithCoordinates() throws IOException {
        statusUpdateActions.createTweetWithLocation("test3", 37.7821120598956, -122.400612831116);
        List<UserPosts> userPosts = userTimelineActions.getUserPosts();
        String actualCountry = userPosts.get(0).getPlace().getCountry();
        assertThat("Country is wrong", actualCountry, equalTo("United States"));

        String actualCityName = userPosts.get(0).getPlace().getFullName();
        assertThat("Name of city is wrong", actualCityName, equalTo("San Francisco, CA"));

        String actualCountryCode = userPosts.get(0).getPlace().getCountryCode();
        assertThat("Country code is wrong", actualCountryCode, equalTo("US"));

    }
}