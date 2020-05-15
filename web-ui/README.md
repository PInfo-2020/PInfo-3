# WebUi

For import node_module in web-ui `npm install` (go it one time after git pull)

For run docker-compose in docker-compose `docker-compose -f docker-compose-Roxane.yml up`

Go to chrome `localhost:8080`

`Administration console`

Username : admin
Password : password

Click on Master and choose `Add realm`

Import : Select file and select `docker-compose/realm-export.json`

Keycloak is good now ! 

Go in web-ui file

`ng serve`

Open chrome and go `localhost:4200`

exit `ng serve` : ctrl + C

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 9.0.7.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).
