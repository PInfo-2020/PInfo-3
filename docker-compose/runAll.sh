docker stop $(docker ps -aq)
docker rm $(docker ps -aq)
docker rmi pro:latest li:latest rece:latest ing:latest
docker volume rm $(docker volume ls)

docker build -t li:latest test-data/listsSQL/
docker build -t ing:latest test-data/ingredSQL/
docker build -t pro:latest test-data/profileSQL/
docker build -t rece:latest test-data/recipeSQL/

docker-compose -f docker-compose-TempMicroservices.yml up &
docker-compose -f docker-compose-Roxane.yml up &
