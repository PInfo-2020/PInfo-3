#!/bin/bash

docker stop instrument-service
docker rm instrument-service

docker stop valuation-service
docker rm valuation-service

docker stop counterparty-service
docker rm counterparty-service

docker stop counterparty-database
docker rm counterparty-database

docker stop instrument-database
docker rm instrument-database


docker stop zookeeper
docker rm zookeeper

docker stop kafka
docker rm kafka

docker stop iam-db
docker rm iam-db

docker stop kong-database
docker rm kong-database


docker stop api-gateway
docker rm api-gateway

docker stop iam
docker rm iam

docker stop kibana
docker rm kibana
