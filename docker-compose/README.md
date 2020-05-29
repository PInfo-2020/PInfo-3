						!All about docker-compose folder!

						!GENERAL INFO!
- the corresponding ports used in all files:
    - lists-service                 port: 12085
    - ingredients-service           port: 12084
    - recepies-service              port: 12082
    - profile-service               port: 12083
    - kafka			    port: 9092
    - keycloak                      port: 8080

- mapping of urls done by api-gatway:
	- http://localhost:12085/lists ------------>  http://localhost/api/v1/lists
	- http://localhost:12084/ingredients ------>  http://localhost/api/v1/ingredients
	- http://localhost:12083/profile ---------->  http://localhost/api/v1/profile
	- http://localhost:12082/recipes ---------->  http://localhost/api/v1/recipes

- docker-compose files will use the images either the images on the docker-site or the local ones


			               !docker-compose-TempMicroservices.yml!
- So the docker-compose-TempMicroservices.yml is for the time when we don't use the external databases and use instead the h2 internal databases.
- It runs the microservices below:
	- lists-service			
	- ingredients-service	 	
	- recepies-service		
	- profile-service		
	- kafka

- command: docker-compose -f docker-compose-TempMicroservices.yml up


					!docker-compose-api-gw.yml!
- the docker-compose-api-gw.yml is for running:
	- apigetway
	- keycloak and its external databaase

- command: docker-compose -f docker-compose-api-gw.yml up


					!docker-compose-Roxane!
- the docker-compose-Roxane is for running ONLY the keycloak with its internal h2 database.

- command: docker-compose -f docker-compose-Roxane.yml up

					!docker-compose-Luka-Nathan-kafka.yml!
- docker-compose-Luka-Nathan-kafka.yml is for Luka and Nathan when they want to test the kafka for their microservices:
- you don't need api-gateway to test kafka
- it runs:
	- recepies-service
	- lists-service
	- kafka
- command: docker-compose -f docker-compose-Luka-Nathan-kafka.yml up

					!docker-compose-Luka-Salma-kafka.yml!
- docker-compose-Luka-Salma-kafka.yml is for Luka and Salma when they want to test the kafka for their microservices:
- you don't need api-gateway to test kafka
- it runs:
	- recepies-service
	- profile-service
	- kafka
- command: docker-compose -f docker-compose-Luka-Salma-kafka.yml up


						!We don't use this for now!

- So the docker-compose-microservices.yml is for the time when we use the external databases.
- It runs the microservices below:
	- lists-service
	- ingredients-service
	- recepies-service
	- profile-service
	- kafka

- command: docker-compose -f docker-compose-microservices.yml up

##############################################################################################################################################
