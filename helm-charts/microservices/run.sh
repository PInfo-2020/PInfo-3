kubectl create secret generic keycloak-realm-secret --from-file=../../docker-compose/realm-export.json 

kubectl delete configmap lists-scripts
kubectl create configmap lists-scripts  --from-file ../../docker-compose/test-data/listsSQL/lists_test_data.sql 

kubectl delete configmap ingred-scripts
kubectl create configmap ingred-scripts  --from-file ../../docker-compose/test-data/ingredSQL/ingredients.sql


kubectl delete configmap profile-scripts
kubectl create configmap profile-scripts  --from-file ../../docker-compose/test-data/profileSQL/profile_test_data.sql


kubectl delete configmap rec-scripts
kubectl create configmap rec-scripts  --from-file ../../docker-compose/test-data/recipeSQL/recipes_test_data.sql


microk8s.helm install random .
