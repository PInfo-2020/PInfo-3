 [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=PInfo-2020_PInfo-3&metric=coverage)](https://sonarcloud.io/dashboard?id=PInfo-2020_PInfo-3) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=PInfo-2020_PInfo-3&metric=alert_status)](https://sonarcloud.io/dashboard?id=PInfo-2020_PInfo-3) [![Build Status](https://travis-ci.com/PInfo-2020/PInfo-3.svg?branch=master)](https://travis-ci.com/PInfo-2020/PInfo-3)
# DIGESTEVE

Repository for PInfo-2020's team 3

## Team members:

* **Roxane Burri** - *Project Manager*
* **Luka Lukic** - *Developer*
* **Salma Ennaji** - *Developer*
* **Nathan Cohen** - *Developer*
* **Benjamin Fischer** - *Developer*
* **Elham Amin Mansour** - *System Administrator*

## How to set up your project:

Yoou have two choices to set up yoour project:

- If you want to start from the project at [hostettler/microservices](https://github.com/hostettler/microservices), simply copy and paste the contents of the repository into your project (except for the `.git` folder and the `README.md`) and push everything to your repository. You will then modify the project as you advance in your work and replace the services in it.

- If you'd rather start a project from the ground up, you can simply delete all the contents of your repository after you've completed the TODO list below, and add your own microservices and web-ui to your project when you'll start working on them.

Below is the list of tasks you will need to complete to set up continuous integration and continuous deployment in your project:

1. As mentionned above, begin by copy-pasting the contents of [hostettler/microservices](https://github.com/hostettler/microservices) (except for the `README.md` and the `.git` folder) into your project.

2. Replace the title of the README.md with your project's name.

3. Replace the placeholders in the team members list with the real names and roles of the members in your team.

4. Travis CI is already configured to watch over your repository. It will execute whatever commands you put into your project's `.travis.yml` file. To see the result of Travis CI after you've pushed something on your repository, go to the [Travis CI website](https://travis-ci.org/) and sign in with your GitHub account. You might need to go to the `settings` page and add *PInfo-2020* to your list of authorized organizations. After you've pushed the microservices to your project, you should see a failed build. This is normal.

5. Go to the [Sonarcloud website](https://sonarcloud.io/) and sign in with GitHub. Again, you will need to add the *PInfo-2020* organization to your account. Once this is done, follow the instructions to set up the analysis for your project's repository. 

6. Update the `.travis.yml` file with your own Sonarcloud project key, organization and login. Do the same in the `sonar-project.properties` file.

7. Go to the [DockerHub website](https://hub.docker.com/) and create an account for your team.

8. Go to your repository's settings on Github in the *secrets* tab, and add two new secrets called `DOCKER_USERNAME` and `DOCKER_PASSWORD` with your DockerHub's account name and password, respectively.

9. Replace `stevehostettler` with your DockerHub's account name in the `.travis.yml` file on the `docker tag ...` and `docker push ...` lines.

10. The last step is to set up the continuous deployment of the application on your team's server. Try to find how to do this ! Hint 1: Things should happen in the `.travis.yml` file. Hint 2: SSH is your friend. Hint 3: For the time being, you can just use docker-compose (you will see how to use Kubernetes later in the course). You might find some useful commands in the second blog post on hostettler.net.

Feel free to remove the steps above from your README.md once you're done with the set up.
