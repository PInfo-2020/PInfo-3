docker stop $(docker ps -aq)
docker rm $(docker ps -aq)

docker-compose -f docker-compose-TempMicroservices.yml up &
docker-compose -f docker-compose-Roxane.yml up &
