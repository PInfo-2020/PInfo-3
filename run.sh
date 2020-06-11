sudo microk8s enable helm3 
sudo microk8s kubectl cluster-info --kubeconfig=${HOME}/.kube/config
sudo microk8s.helm3 delete random --kubeconfig=${HOME}/.kube/config
sudo microk8s.helm3 init --kubeconfig=${HOME}/.kube/config 
sudo microk8s.helm3 repo add penguin https://pinfo-2020.github.io/PInfo-3/ --kubeconfig=${HOME}/.kube/config 
sudo microk8s.helm3 repo update --kubeconfig=${HOME}/.kube/config 
sudo microk8s.helm3 install penguin/microservices --name random --kubeconfig=${HOME}/.kube/config 
sudo microk8s.helm3 status random --kubeconfig=${HOME}/.kube/config 


sudo microk8s.kubectl create secret generic keycloak-realm-secret --from-file=./docker-compose/realm-export.json --kubeconfig=${HOME}/.kube/config

sudo microk8s.kubectl delete configmap lists-scripts --kubeconfig=${HOME}/.kube/config
sudo microk8s.kubectl create configmap lists-scripts  --from-file ./helm-charts/microservices/test-data/lists_test_data.sql --kubeconfig=${HOME}/.kube/config

sudo microk8s.kubectl delete configmap ingred-scripts --kubeconfig=${HOME}/.kube/config
sudo microk8s.kubectl create configmap ingred-scripts  --from-file ./helm-charts/microservices/test-data/ingredients.sql --kubeconfig=${HOME}/.kube/config


sudo microk8s.kubectl delete configmap profile-scripts --kubeconfig=${HOME}/.kube/config
sudo microk8s.kubectl create configmap profile-scripts  --from-file ./helm-charts/microservices/test-data/profile_test_data.sql --kubeconfig=${HOME}/.kube/config


sudo microk8s.kubectl delete configmap rec-scripts --kubeconfig=${HOME}/.kube/config
sudo microk8s.kubectl create configmap rec-scripts  --from-file ./helm-charts/microservices/test-data/recipes_test_data.sql --kubeconfig=${HOME}/.kube/config

#sudo microk8s enable helm3 --kubeconfig=${HOME}/.kube/config 
#sudo microk8s.helm3 install random . --kubeconfig=${HOME}/.kube/config
sudo microk8s.kubectl patch service random-nginx-ingress-controller    -p '{"spec":{"externalIPs":["129.194.69.134"]}}' --kubeconfig=${HOME}/.kube/config
sudo microk8s.kubectl patch service random-microservices-recipes-service    -p '{"spec":{"externalIPs":["129.194.69.134"], "path":["/api/v1/recipe"]}}' --kubeconfig=${HOME}/.kube/config
sudo microk8s.kubectl patch service random-microservices-lists-service     -p '{"spec":{"externalIPs":["129.194.69.134"], "path":["/api/v1/lists"]}}' --kubeconfig=${HOME}/.kube/config
sudo microk8s.kubectl patch service random-microservices-profile-service    -p '{"spec":{"externalIPs":["129.194.69.134"], "path":["/api/v1/profiles"]}}' --kubeconfig=${HOME}/.kube/config
sudo microk8s.kubectl patch service random-microservices-ingredients-service    -p '{"spec":{"externalIPs":["129.194.69.134"], "path":["/api/v1/ingredients"]}}' --kubeconfig=${HOME}/.kube/config
