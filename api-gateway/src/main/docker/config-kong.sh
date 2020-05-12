#Creates the services.
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=lists-service" --data-urlencode "url=http://129.194.69.134:12085/lists"
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=ingredients-service" --data-urlencode "url=http://129.194.69.134:12084/ingredients"
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=profile-service" --data-urlencode "url=http://129.194.69.134:12083/profile"
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=recipes-service" --data-urlencode "url=http://129.194.69.134:12082/recipes"
#Creates the routes
curl -S -s -i -X POST  --url http://api-gateway:8001/services/lists-service/routes --data-urlencode "paths[]=/api/v1/lists" 
curl -S -s -i -X POST  --url http://api-gateway:8001/services/ingredients-service/routes   --data-urlencode "paths[]=/api/v1/ingredients"
curl -S -s -i -X POST  --url http://api-gateway:8001/services/profile-service/routes   --data-urlencode "paths[]=/api/v1/profile"  
curl -S -s -i -X POST  --url http://api-gateway:8001/services/recipes-service/routes    --data-urlencode "paths[]=/api/v1/recipes" 
#Enable the Open ID Plugin
#curl -S -s -i -X POST  --url http://api-gateway:8001/plugins --data "name=jwt" --data "enabled=true"
#curl -S -s -i -X POST  --url http://api-gateway:8001/consumers  --data "username=api-sso-proxied"   --data "custom_id=api-sso-proxied"
#curl -S -s -i -X POST  --url http://api-gateway:8001/consumers/api-sso-proxied/jwt   -F "algorithm=RS256"  -F "rsa_public_key=@/tmp/keycloak_rsa_provider-key-pub.pem" -F "key=https://localhost/auth/realms/apigw"
#curl -S -s -i -X POST  --url http://api-gateway:8001/consumers  --data "username=api-sso-not-proxied"   --data "custom_id=api-sso-not-proxied"
#curl -S -s -i -X POST  --url http://api-gateway:8001/consumers/api-sso-not-proxied/jwt   -F "algorithm=RS256"  -F "rsa_public_key=@/tmp/keycloak_rsa_provider-key-pub.pem" -F "key=http://iam:8080/auth/realms/apigw"
