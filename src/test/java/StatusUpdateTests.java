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

//    TODO: check naming across project
//    TODO: check all assertion namings
    @Test
    public void checkRussianLanguage() throws IOException {
        NewPost newPost = statusUpdateActions.postStatus("здорова");
        String currentLanguage = newPost.getLang();
        assertThat("Language is not Russian", currentLanguage, equalTo("ru"));
    }

    @Test
    public void checkEnglishLanguage() throws IOException {
        NewPost newPost = statusUpdateActions.postStatus("hello");
        String currentLanguage = newPost.getLang();
        assertThat("Language is not English", currentLanguage, equalTo("en"));
    }

    @Test
    public void checkTextOfNewPost() throws IOException {
        String textFromRequest = "some text";

        statusUpdateActions.postStatus(textFromRequest);
        List<UserPosts> userPosts = userTimelineActions.getUserInfo();

        String textFromResponse = userPosts.get(0).getText();
        assertThat("Text from response differs from request. Your request: " + textFromRequest + ". Your response: " + textFromResponse,
                textFromResponse, equalTo(textFromResponse));
    }

    @Test
    public void checkIDsAfterReply() throws IOException {
        NewPost firstPost = statusUpdateActions.postStatus("test1");
        long idOfFirstPost = firstPost.getId();

        statusUpdateActions.postStatus("test2", idOfFirstPost);

        List<UserPosts> userPosts = userTimelineActions.getUserInfo();
        long actualReplyId = userPosts.get(0).getIn_reply_to_status_id();

        assertThat("id's are differed", idOfFirstPost, equalTo(actualReplyId));
    }

    @Test
    public void checkPlaceWithCoordinates() throws IOException {
        statusUpdateActions.postStatus("test3", 37.7821120598956, -122.400612831116);
        List<UserPosts> userPostsq = userTimelineActions.getUserInfo();
        String actualCountry = userPostsq.get(0).getPlace().getCountry();
        assertThat("Country is wrong", actualCountry, equalTo("United States"));

        String actualCityName = userPostsq.get(0).getPlace().getFull_name();
        assertThat("Name of city is wrong", actualCityName, equalTo("San Francisco, CA"));

        String actualCountryCode = userPostsq.get(0).getPlace().getCountry_code();
        assertThat("Country code is wrong", actualCountryCode, equalTo("US"));
    }
}