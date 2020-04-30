#FROM nginx:alpine

#COPY ./dist /usr/share/nginx/html
#COPY nginx.default /etc/nginx/conf.d/default.conf

#EXPOSE 80


#Stage 1: Build the application.

FROM node:latest AS build-phase

WORKDIR /webapp

COPY package*.json /webapp/

RUN npm install

COPY ./ /webapp/

RUN npm run build -- --output-path=./dist 

# Stage 2: Deploy the application on an Nginx server.
FROM nginx:latest

COPY --from=build-phase /webapp/dist/ /usr/share/nginx/html

#FROM nginx:1.17.1-alpine
#COPY /dist /usr/share/nginx/html
