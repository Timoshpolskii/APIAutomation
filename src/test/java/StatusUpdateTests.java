package test.java;

import main.java.Actions.ApiActions;
import main.java.Response.StatusUpdate.NewTweet;
import main.java.Response.UserTimeline.UserTweets;
import org.testng.annotations.Test;

import static main.java.Support.SingletonApiRequests.getApiRequests;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.List;

public class StatusUpdateTests {
    ApiActions apiActions = new ApiActions();

    @Test
    public void checkRussianLanguage() throws IOException {

        NewTweet newTweet = (NewTweet) apiActions.execute(getApiRequests().createNewTweet("привет"));

        String currentLanguage = newTweet.getLang();
        assertThat("Language is not Russian", currentLanguage, equalTo("ru"));
    }

    @Test
    public void checkEnglishLanguage() throws IOException {

        NewTweet newTweet = (NewTweet) apiActions.execute(getApiRequests().createNewTweet("hello"));

        String currentLanguage = newTweet.getLang();
        assertThat("Language is not English", currentLanguage, equalTo("en"));
    }

    @Test
    public void checkTextOfNewPost() throws IOException {

        String text = "some text";
        apiActions.execute(getApiRequests().createNewTweet(text));

        List<UserTweets> userTweets = (List<UserTweets>) apiActions.execute(getApiRequests().getUserTweets());

        String textFromResponse = userTweets.get(0).getText();
        assertThat("Text from response differs from request.", textFromResponse, equalTo(text));
    }

    @Test
    public void checkIDsAfterReply() throws IOException {

        NewTweet firstTweet = (NewTweet) apiActions.execute(getApiRequests().createNewTweet("test1"));
        long idOfFirstTweet = firstTweet.getId();

        apiActions.execute(getApiRequests().replyToTweet("test2", idOfFirstTweet));

        List<UserTweets> userTweets = (List<UserTweets>) apiActions.execute(getApiRequests().getUserTweets());

        long actualReplyId = userTweets.get(0).getInReplyToStatusId();

        assertThat("ID's are differed", idOfFirstTweet, equalTo(actualReplyId));
    }

    @Test
    public void checkPlaceWithCoordinates() throws IOException {

        apiActions.execute(getApiRequests().createTweetWithLocation("test3", 37.821, -122.400));

        List<UserTweets> userTweets = (List<UserTweets>) apiActions.execute(getApiRequests().getUserTweets());

        String actualCountry = userTweets.get(0).getPlace().getCountry();
        assertThat("Country is wrong", actualCountry, equalTo("United States"));

        String actualCityName = userTweets.get(0).getPlace().getFullName();
        assertThat("Name of city is wrong", actualCityName, equalTo("California, USA"));

        String actualCountryCode = userTweets.get(0).getPlace().getCountryCode();
        assertThat("Country code is wrong", actualCountryCode, equalTo("US"));

    }
}