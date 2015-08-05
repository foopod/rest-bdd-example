Feature: Rest Service
  As a Web Service User
  I want to use a CRUD restful webservice
  So that I can manage my music for stand ups

  Scenario: Get a list of all the Songs
    Given the REST service knows about the following songs
    	|	title					|	artist			|	submitter	|	url																		|
    	|	Who let the dogs out	|	Baha Men		|	Jono		|	https://www.youtube.com/watch?feature=player_detailpage&v=He82NBjJqf8	|
    	|	Stairway to Heaven		|	Led Zeppelin	|	Jono		|	https://www.youtube.com/watch?feature=player_detailpage&v=BcL---4xQYA	|
    When I do a GET request on /songs
    Then the respoonse should be JSON:
    	"""
    	[
    		{
    			"title" : "Who let the dogs out",
    			"artist" : "Baha Men",
    			"submitter" : "Jono",
    			"url" : "https://www.youtube.com/watch?feature=player_detailpage&v=He82NBjJqf8"
    		},
    		{
    			"title" : "Who let the dogs out",
    			"artist" : ,
    			"submitter" : "Jono",
    			"url" : "https://www.youtube.com/watch?feature=player_detailpage&v=BcL---4xQYA"
    		}
    	]
    	"""