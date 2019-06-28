Spring boot application which has
`/simulate` endpoint, that accepts:
- PUT request 
- with `ticks` parameter of `int`

Also there is a `GET /health` endpoint to check that application is up 

Then it does a simulation

###Simulation logic

Consider an infinite grid of white and black squares. 
The grid is initially all white and there is a machine in one cell facing right. 
It will move based on the following rules:
- If the machine is in a white square, turn 90° clockwise and move forward 1 unit;
- If the machine is in a black square, turn 90° counter-clockwise and move forward 1 unit;
- At every move flip the color of the base square.

Implement an application that will receive HTTP PUT requests 
with a number of steps the simulation should run, always starting from the same conditions;

The resulting grid is save to a file and 
the path to the file is then returned to a user in HTTP response
With a default configuration, newly created file is `/tmp/%random_uuid%.txt`

###Running from command line

From root directory of the project:

`$ mvn clean package`
`$ java -jar target/grid-simulator.jar`

Access via `http://localhost:8080/health`

###Running from a container

1. On a build agent, having javac for Java 11+: build jar artifact. 

`$ mvn clean package`

1. On a build agent, having Docker and results of previous step: (preferably, on the machine use in previous step) 
build a container in the results directory 

`$ docker build -t grid-simulator . `

1. Push/pull to/from Docker Hub/Artifactory as needed.
Run docker file, exposing the port `8080`

`$ docker run --rm -it -p 8080:8080/tcp grid-simulator`

Altogether (on the same machine):
`mvn clean package && docker build -t grid-simulator .  && docker run --rm -it -p 8080:8080/tcp grid-simulator`

Then access via `http://localhost:8080/health`

TODO
- Deploy to Elastic BeanStalk with Terraform
- Document mounting directory for file storing from docker container via
environment variables and Docker `-v` flag 
- Add tests for actual result, add unit tests, add Rest Assured specification for the endpoints
- Travis CI


