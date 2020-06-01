import { Component, OnInit, AfterViewInit } from '@angular/core';
import { KeycloakService } from '../services/keycloak/keycloak.service';
import { KeycloakInstance } from 'keycloak-js';
import { Router } from '@angular/router';
import { ProfileService } from './profileService';
import { User } from './user';
import { Recipe } from './recipe';
import { PlannedRecipe } from './plannedRecipe';
import { RecipeService } from './recipeService';
import { Observable, forkJoin } from 'rxjs';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit, AfterViewInit {
  userDB: User;
  plannedRecipeDB: Array<PlannedRecipe> = [];
  recipeDB: Array<Recipe> = [];

  usernameElem: any;
  emailElem: any;
  passwordElem: any;
  ratingElem: any;



  public keycloakAuth: KeycloakInstance;

  constructor(public keycloak: KeycloakService, private profileService: ProfileService, private recipeService: RecipeService, private router: Router){}

  ngOnInit(): void {
    this.keycloakAuth = this.keycloak.getKeycloakAuth();
    if (this.keycloak.isLoggedIn() === false) {
        this.keycloak.login();
    }
  }

  ngAfterViewInit() {
    this.usernameElem = document.getElementById("username");
    this.emailElem = document.getElementById("email");
    this.passwordElem = document.getElementById("password");
    this.ratingElem = document.getElementById("rating");

    let ingredientDataElem = document.getElementById("ingredients-choices");
    let that = this;

    let url = this.router.url;
    let split = url.split("profile/");
    let id = Number(split[1]);

    this.profileService.getUser(id)
      .subscribe((data: User) => {
        this.userDB = data;
        this.callPlannedRecipe(id);
        this.callMyRecipe(3);
        this.setUser();
      });
  }

  setUser(){
    this.usernameElem.innerHTML = this.userDB.username;
    this.ratingElem.innerHTML = this.userDB.score;
  }

  callPlannedRecipe(id){
    this.profileService.getPlannedRecipe(id)
      .subscribe((data: PlannedRecipe[]) => {
        this.plannedRecipeDB = data;
        this.callPlannedRecipeScore();
      });
  }

  callMyRecipe(id){
    this.recipeService.getMyRecipe(id)
      .subscribe((data: Recipe[]) => {
        this.recipeDB = data;
        console.log(this.recipeDB);
        this.callMyRecipeScore();
      });
  }

  setPlannedRecipe(recipe, rating){
    let blockContainer = document.getElementById("div1");

    let div = document.createElement("div");
    div.className = "row mb-1 text-center border";
    div.style.cssText = "border-width: thick !important;";
    div.innerHTML = `
      <div class="row w-100 mb-2">
        <div class="col m-auto font-weight-bold">${recipe.name}</div><div class="col-2 m-auto text-right">${rating}</div><img src="../assets/img/star.png" />
      </div>
      <div class="row w-100">
        <span class="col m-auto">${recipe.description}</span>
      </div>
    `;
    blockContainer.appendChild(div);
  }

  setMyRecipe(recipe, rating){
    let blockContainer = document.getElementById("div2");

    let div = document.createElement("div");
    div.className = "row mb-1 text-center border";
    div.style.cssText = "border-width: thick !important;";
    div.innerHTML = `
      <div class="row w-100 mb-2">
        <div class="col m-auto font-weight-bold">${recipe.name}</div><div class="col-2 m-auto text-right">${rating}</div><img src="../assets/img/star.png" />
      </div>
      <div class="row w-100">
        <span class="col m-auto">${recipe.description}</span>
      </div>
    `;
    blockContainer.appendChild(div);
  }

  callPlannedRecipeScore(){
    let recipe: Recipe;
    let rating: Number;
    let observables = this.plannedRecipeDB.map(item => this.recipeService.getPlannedRecipe(item.recipeID)
    .subscribe(data => {
      recipe = data[0];
      rating = data[1];
      if (recipe !== null) {
        this.setPlannedRecipe(recipe,rating);
      }
    }));
  }

  callMyRecipeScore(){
    let recipe: Recipe;
    let rating: Number;
    let observables = this.recipeDB.map(item => this.recipeService.getMyRecipeScore(item.id)
    .subscribe(data => {
      recipe = item;
      rating = data;
      if (recipe !== null) {
        this.setMyRecipe(recipe,rating);
      }
    }));
  }
}
