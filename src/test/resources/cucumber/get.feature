Feature: GET Request on Rest Server
  As a Web Service User
  I want to use a GET request
  So that I can manage my music for stand ups
  
  Scenario: Get songs where no songs exist
    Given the REST service knows about no songs
    When I do a GET request on /songs
    Then the response should be empty

  Scenario: Get songs where there are 2 songs
    Given the REST service knows about the following songs
    # |	title				         	|	artist			  | url                                                                    |	submitter |
    	|	Who let the dogs out	|	Baha Men	   	| https://www.youtube.com/watch?feature=player_detailpage&v=He82NBjJqf8  |	Jono	    |
    	|	Stairway to Heaven		|	Led Zeppelin	| https://www.youtube.com/watch?feature=player_detailpage&v=BcL---4xQYA  |	Jono	    |
    When I do a GET request on /songs
    Then the response should be JSON:
    	"""
    	[
    		{
    			"title" : "Who let the dogs out",
    			"artist" : "Baha Men",
    			"submitter" : "Jono",
    			"url" : "https://www.youtube.com/watch?feature=player_detailpage&v=He82NBjJqf8"
    		},
    		{
    			"title" : "Stairway to Heaven",
    			"artist" : "Led Zeppelin",
    			"submitter" : "Jono",
    			"url" : "https://www.youtube.com/watch?feature=player_detailpage&v=BcL---4xQYA"
    		}
    	]
    	"""
