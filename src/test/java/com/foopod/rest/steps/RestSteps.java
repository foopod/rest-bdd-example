package com.foopod.rest.steps;

import java.util.List;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.PendingException;
import com.foopod.rest.StandUpSongWebService;

public class RestSteps{
	@Given("^the REST service knows about the following songs$")
	public void the_REST_service_knows_about_the_following_songs(List<List<String>> listOfSongs) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	    // E,K,V must be a scalar (String, Integer, Date, enum etc)
	    
	   	//TODO Start webservice
	   	//TODO Remove all songs
	   	//TODO Add all songs in list to service
	   	// StandUpSongWebService service = new StandUpSongWebService();
	    throw new PendingException();
	}

	@When("^I do a GET request on /songs$")
	public void i_do_a_GET_request_on_songs() throws Throwable {
	    //TODO make a GET request on /songs

	    throw new PendingException();
	}

	@Then("^the respoonse should be JSON:$")
	public void the_respoonse_should_be_JSON(String arg1) throws Throwable {
	    //TODO compare the rest services response to what we think it should be

	    throw new PendingException();
	}
}