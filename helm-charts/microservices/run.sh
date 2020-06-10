microk8s.kubectl create secret generic keycloak-realm-secret --from-file=../../docker-compose/realm-export.json

microk8s.kubectl delete configmap lists-scripts
microk8s.kubectl create configmap lists-scripts  --from-file ../../docker-compose/test-data/listsSQL/lists_test_data.sql

microk8s.kubectl delete configmap ingred-scripts
microk8s.kubectl create configmap ingred-scripts  --from-file ../../docker-compose/test-data/ingredSQL/ingredients.sql


microk8s.kubectl delete configmap profile-scripts
microk8s.kubectl create configmap profile-scripts  --from-file ../../docker-compose/test-data/profileSQL/profile_test_data.sql


microk8s.kubectl delete configmap rec-scripts
microk8s.kubectl create configmap rec-scripts  --from-file ../../docker-compose/test-data/recipeSQL/recipes_test_data.sql

microk8s enable helm3
microk8s.helm3 install random .
microk8s.kubectl patch service random-nginx-ingress-controller    -p '{"spec":{"externalIPs":["129.194.69.134"]}}'
microk8s.kubectl patch service random-microservices-recipes-service    -p '{"spec":{"externalIPs":["129.194.69.134"], "path":["/api/v1/recipe"]}}'
microk8s.kubectl patch service random-microservices-lists-service     -p '{"spec":{"externalIPs":["129.194.69.134"], "path":["/api/v1/lists"]}}'
microk8s.kubectl patch service random-microservices-profile-service    -p '{"spec":{"externalIPs":["129.194.69.134"], "path":["/api/v1/profiles"]}}'
microk8s.kubectl patch service random-microservices-ingredients-service    -p '{"spec":{"externalIPs":["129.194.69.134"], "path":["/api/v1/ingredients"]}}'
