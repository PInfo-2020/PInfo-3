import { Component, OnInit, AfterViewInit } from '@angular/core';
import { KeycloakService } from '../services/keycloak/keycloak.service';
import { KeycloakInstance } from 'keycloak-js';
import { RecipeService } from './recipeService';
import { Recipe } from './recipe';
import { IngredientService } from './ingredientService';
import { Ingredient } from './ingredient';
import { IngredientRecipe } from './ingredientRecipe';
import { Router } from '@angular/router';

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css']
})
export class RecipeComponent implements OnInit, AfterViewInit {

  recipeDB: Recipe;
  ingredientsDB: Array<String> = [];
  ingredientsRecipeDB: Array<IngredientRecipe> = [];
  counterInstruction: number = 1;
  nameElem: any;
  peopleElem: any;
  timeElem: any;
  gradeElem: any;
  newGradeElem: any;
  descriptionElem: any;
  ingredientElem: any;
  quantityElem: any;
  instructionElem: any;
  commentElem: any;

  public keycloakAuth: KeycloakInstance;
  constructor(public keycloak: KeycloakService, private recipeService: RecipeService, private ingredientService: IngredientService, private router: Router) { }

  ngOnInit(): void {
    // this.keycloakAuth = this.keycloak.getKeycloakAuth();
    // if (this.keycloak.isLoggedIn() == false) {
    //   this.keycloak.login();
    // }
  }

  ngAfterViewInit() {

    this.nameElem = document.getElementById("name");
    this.peopleElem = document.getElementById("people");
    this.timeElem = document.getElementById("time");
    this.gradeElem = document.getElementById("grade");
    this.newGradeElem = document.getElementById("new_grade");
    this.descriptionElem = document.getElementById("description");
    this.ingredientElem = document.getElementById("ingredient");
    this.quantityElem = document.getElementById("quantity");
    this.instructionElem = document.getElementById("instruction");

    let url = this.router.url;
    let split = url.split("recipe/");
    let id = Number(split[1]);
    let that = this;

    this.recipeService.getRecipe(id)
      .subscribe((data: Recipe) => {
        that.recipeDB = data;
        console.log(id);
      	that.call1(id);
      	that.call2();
      	that.call3();
      });

  }

  call1(id) {
    let that = this;
    console.log(id);
    this.recipeService.getIngredientsRecipe(id)
      .subscribe((data: IngredientRecipe[]) => {
        that.ingredientsRecipeDB = data;
      });
  }

  call2() {
    let that = this;
    this.ingredientsRecipeDB.forEach(function(item) {
      that.ingredientService.getIngredient(item.ingredientId)
        .subscribe((data: Ingredient) => {
          that.ingredientsDB.push(data.name);
        });
    });
  }

  call3() {
    let that = this;
    console.log(that.recipeDB.name);
    this.nameElem.value = that.recipeDB.name;
    this.peopleElem.value = that.recipeDB.personnes;
    this.timeElem.value = that.recipeDB.minutes;
    this.descriptionElem.value = that.recipeDB.description;
    // this.instructionElem.value = that.recipeDB.instructions;
  }

  add_shop_list() {


  }

  give_grade() {

  }

  add_comment() {
    // this.recipeDB.comment = this.commentElem.value;

  }

  cook() {

  }

}
