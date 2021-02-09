# TransformersProject
SpringBoot Restful Webservice with JPA .h2 Project

How to build and run the project
	- Download Transformers Project from github https://github.com/AnithaPandurangan/TransformersProject.git
	- In eclipse, Import project as a maven project and update dependencies.
	- Goto TransformersApplication.java main file to build and run as Java application

How to run unit test
	- Transformers\src\test\java\Transformers\service\TransformerServiceTest.java
	- Run as java application (Note: Test datas and print statements were added to compare results)

API endpoints with Json payload

- Create a Transformer
	URL:  http://localhost:8080/Transformers/
	Method: POST
	Payload: 

{
  "name": "Autobots",
  "team": "A",
  "strength": 4,
  "intelligence": 4,
  "speed": 4,
  "endurance": 4,
  "rank": 4,
  "courage": 4,
  "firepower": 4,
  "skill": 4
}
	
CURL cmd:
		curl -H "Content-Type: application/json" -X POST -d "{\"name\":\"Autobots\",\"team\":\"A\",\"strength\":4,\"intelligence\":4,\"speed\":4,\"endurance\":4,\"rank\":4,\"courage\":4,\"firepower\":4,\"skill\":4}" http://localhost:8080/Transformers/

- Update a Transformer
	URL:http://localhost:8080/Transformers/
	Method: PUT
	Payload:
{
"id":2,
"name":"BluestreakMaster",
"team":"A",
"strength":6,
"intelligence":6,
"speed":7,
"endurance":9,
"rank":5,
"courage":2,
"firepower":9,
"skill":7
}

CURL cmd:
	curl -H "Content-Type: application/json" -X PUT -d "{\"id\":2,\"name\":\"BluestreakMaster\",\"team\":\"A\",\"strength\":6,\"intelligence\":6,\"speed\":7,\"endurance\":9,\"rank\":5,\"courage\":2,\"firepower\":9,\"skill\":7}" http://localhost:8080/Transformers/

-Delete a Transformer
	URL: http://localhost:8080/Transformers/1
	Method: DELETE

Note: id is passed with url

Curl cmd:
	curl -H "Content-Type: application/json" -X DELETE  http://localhost:8080/Transformers/1

-List Transformers
	URL: http://localhost:8080/Transformers/
	Method: GET

CURL Cmd:
	curl -H "Content-Type: application/json" -X GET http://localhost:8080/Transformers/

-Given a list of Transformer IDs, determine the winning team
	URL: http://localhost:8080/Transformers/FindWinningTeam/
	Method: POST
	Payload:
{
  "TransformerIds":[1,2,3]
}

CURL cmd:
	curl -H "Content-Type: application/json" -X POST -d "{\"TransformerIds\":[1,2,3]}" http://localhost:8080/Transformers/FindWinningTeam/


Any assumptions or notes to the reviewer

- Determine winning team with only one player 
	will return "0 battle..."