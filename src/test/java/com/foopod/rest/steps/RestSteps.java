package com.foopod.rest.steps;

import java.util.List;
import java.util.ArrayList;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.PendingException;
import com.foopod.rest.SongDBService;
import com.foopod.rest.SongList;
import com.foopod.rest.SongChoice;
import com.foopod.rest.helper.HTTPRequester;

public class RestSteps{

	@Given("^the REST service knows about no songs$")
	public void the_REST_service_knows_about_no_songs() throws Throwable {
			// Write code here that turns the phrase above into concrete actions
			throw new PendingException();
	}

	@Given("^the REST service knows about the following songs$")
	public void the_REST_service_knows_about_the_following_songs(List<List<String>> listOfSongs) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	    // E,K,V must be a scalar (String, Integer, Date, enum etc)

			SongDBService songDB = new SongDBService();
			SongList songs = new SongList();
			for(List<String> list : listOfSongs){
				SongChoice tempSong = new SongChoice(list.get(0), list.get(1), list.get(2), list.get(3));
				songs.addSong(tempSong);
			}
			songDB.setUpDBFromList(songs);
	}

	@When("^I do a GET request on /songs$")
	public void i_do_a_GET_request_on_songs() throws Throwable {
	    HTTPRequester request = new HTTPRequester();
			String response = request.makeRequest("GET", "http://localhost:8080/songs");
			System.out.println(response);
	    // throw new PendingException();
	}

	@Then("^the response should be JSON:$")
	public void the_response_should_be_JSON(String arg1) throws Throwable {
	    //TODO compare the rest services response to what we think it should be

	    throw new PendingException();
	}

	@Then("^the response should be empty$")
	public void the_response_should_be_empty() throws Throwable {
			// Write code here that turns the phrase above into concrete actions
			throw new PendingException();
	}
}
