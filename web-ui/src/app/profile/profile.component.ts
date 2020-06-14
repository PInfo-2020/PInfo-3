import { Component, OnInit, AfterViewInit } from '@angular/core';
import { KeycloakService } from '../services/keycloak/keycloak.service';
import { KeycloakInstance } from 'keycloak-js';
import { Router, ActivatedRoute } from '@angular/router';
import { ProfileService } from './profileService';
import { User } from './user';
import { Recipe } from './recipe';
import { PlannedRecipe } from './plannedRecipe';
import { RecipeService } from './recipeService';
import { Observable, forkJoin } from 'rxjs';
import * as $ from 'jquery';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit, AfterViewInit {
  userDB: User;
  plannedRecipeDB: Array<PlannedRecipe> = [];
  recipeDB: Array<Recipe> = [];

  userID: string;

  usernameElem: any;
  emailElem: any;
  ratingElem: any;



  public keycloakAuth: KeycloakInstance;

  constructor(public keycloak: KeycloakService, private profileService: ProfileService, private recipeService: RecipeService, private router: Router, private route: ActivatedRoute){}

  ngOnInit(): void {
    this.keycloakAuth = this.keycloak.getKeycloakAuth();
    if (this.keycloak.isLoggedIn() === false) {
        this.keycloak.login();
    }
    let skip = true;
    this.route.params.subscribe(
     params => {
       if (!skip) {
         skip = true;
         this.ngAfterViewInit();
         skip = false
       }
    });
    skip = false;
  }

  ngAfterViewInit() {
    this.usernameElem = document.getElementById("username");
    this.emailElem = document.getElementById("email");
    this.ratingElem = document.getElementById("rating");

    this.userID = this.router.url.split("profile/")[1]

    let ingredientDataElem = document.getElementById("ingredients-choices");

    this.profileService.getUser(this.userID)
      .subscribe((data: User) => {
        this.userDB = data;
        this.callPlannedRecipe();
        this.callMyRecipe();
        this.setUser();
      });
  }

  setUser(){
    this.usernameElem.innerHTML = this.userDB.username;
    this.ratingElem.innerHTML = this.userDB.score;
    if (this.keycloak.getUsername() == this.userDB.username){
      this.emailElem.innerHTML = this.keycloak.getEmail();
    }
    else {
      this.emailElem.innerHTML = "***********";
    }
  }

  callPlannedRecipe(){
    var mainContainer = document.getElementById("div1");
    while (mainContainer.firstChild) {
      mainContainer.firstChild.remove();
    }
    this.profileService.getPlannedRecipe(this.userID)
      .subscribe((data: PlannedRecipe[]) => {
        this.plannedRecipeDB = data;
        this.callPlannedRecipeScore();
      });
  }

  callMyRecipe(){
    var mainContainer = document.getElementById("div2");
    while (mainContainer.firstChild) {
      mainContainer.firstChild.remove();
    }
    this.recipeService.getMyRecipe(this.userID)
      .subscribe((data: Recipe[]) => {
        this.recipeDB = data;
        console.log(this.recipeDB);
        this.callMyRecipeScore();
      });
  }

  setPlannedRecipe(recipe, rating){
    let blockContainer = document.getElementById("div1");
    let buttonId = "plannedRecipe".concat(recipe.id);

    let div = document.createElement("div");
    div.className = "row mb-1 text-center border rounded border-white";
    div.style.cssText = "border-width: thick !important;";
    div.innerHTML = `
      <div class="row w-100 mb-2">
        <div class="col m-auto font-weight-bold">${recipe.name}</div>
        <div class="col-2 m-auto text-right">${rating}</div>
        <img src="../assets/img/star.png" />
      </div>
      <div class="row w-100">
        <span class="col m-auto">${recipe.description}</span>
        <button class="col-2 btn btn-primary" id=${buttonId}>Go to</button>
      </div>
    `;
    blockContainer.appendChild(div);
    let that = this;
    $("#".concat(buttonId)).click(function(){
      that.router.navigate(["/recipe", recipe.id])
    });
  }

  setMyRecipe(recipe, rating){
    let blockContainer = document.getElementById("div2");
    let buttonId = "myRecipe".concat(recipe.id);

    let div = document.createElement("div");
    div.className = "row mb-1 text-center border rounded border-white";
    div.style.cssText = "border-width: thick !important;";
    div.innerHTML = `
      <div class="row w-100 mb-2">
        <div class="col m-auto font-weight-bold">${recipe.name}</div>
        <div class="col-2 m-auto text-right">${rating}</div>
        <img src="../assets/img/star.png" />
      </div>
      <div class="row w-100">
        <span class="col m-auto">${recipe.description}</span>
        <button class="col-2 btn btn-primary" id=${buttonId}>Go to</button>
      </div>
    `;
    blockContainer.appendChild(div);
    let that = this;
    $("#".concat(buttonId)).click(function(){
      that.router.navigate(["/recipe", recipe.id])
    });
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
