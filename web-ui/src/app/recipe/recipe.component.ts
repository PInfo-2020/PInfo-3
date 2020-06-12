import { Component, OnInit, AfterViewInit } from '@angular/core';
import { KeycloakService } from '../services/keycloak/keycloak.service';
import { KeycloakInstance } from 'keycloak-js';
import { RecipeService } from './recipeService';
import { Recipe } from './recipe';
import { ItemCart } from './itemcart';
import { ListService } from './listService';
import { ProfileService } from './profileService';
import { Grade } from './grade';
import { Comment } from './comment';
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

  userID: string = this.keycloak.getKeycloakId();
  recipeDB: Recipe;
  itemCartDB: Array<ItemCart> = [];
  ingredientsRecipeDB: Array<IngredientRecipe> = [];
  ingredientsDB: Array<Ingredient> = [];
  commentsDB: Array<Comment> = [];
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
  constructor(public keycloak: KeycloakService, private recipeService: RecipeService, private ingredientService: IngredientService,
              private listService: ListService, private profileService: ProfileService, private router: Router) { }

  ngOnInit(): void {
    this.keycloakAuth = this.keycloak.getKeycloakAuth();
    if (this.keycloak.isLoggedIn() == false) {
      this.keycloak.login();
    }

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
    this.commentElem = document.getElementById("comment")

    let url = this.router.url;
    let split = url.split("recipe/");
    let id = Number(split[1]);
    let that = this;

    this.recipeService.getRecipe(id)
      .subscribe((data: Recipe) => {
        that.recipeDB = data;
      	that.call1(id);
        that.call3(id);
        that.call6(id);
      });

  }

  call1(id) {
    let that = this;
    this.recipeService.getIngredientsRecipe(id)
      .subscribe((data: IngredientRecipe[]) => {
        that.ingredientsRecipeDB = data;
        //that.call9();
        that.call2();
        that.call4();
    });
  }

  call2() {
    let ingredient: Ingredient;
    let quantite: Number;
    this.ingredientsRecipeDB.map(item => this.ingredientService.getIngredient(item.ingredientId)
    .subscribe((data: Ingredient) => {
      this.ingredientsDB.push(data);
      ingredient = data;
      quantite = item.quantite;
      this.call5(ingredient, quantite);
    }));
  }

  call3(id) {
    let that = this;
    this.recipeService.getGrade(id)
      .subscribe((data: Number) => {
        that.gradeElem.innerHTML = data;
      });
  }

  call4() {
    let that = this;
    this.nameElem.innerHTML = that.recipeDB.name;
    this.peopleElem.innerHTML = that.recipeDB.personnes;
    this.timeElem.innerHTML = that.recipeDB.minutes;
    this.descriptionElem.innerHTML = that.recipeDB.description;
    let instructions = that.recipeDB.instructions.split("///");
    for (var i = 1; i <= instructions.length; i++) {
      let blockToAdd = document.createElement("div");
      blockToAdd.className = "row mb-1 text-center";
      blockToAdd.innerHTML = `
        <span class="m-auto font-weight-bold bg-white pl-2 pr-2 border">${i}.</span>
        <span class="col-8 border m-auto border bg-white">${instructions[i-1]}</span>`;
      let blockContainer = document.getElementById("instructions_listing");
      blockContainer.appendChild(blockToAdd);
    }
  }

  // call9() {
  //   let userID = this.userID;
  //   let that = this;
  //   this.fridgeService.getAllFridge(userID)
  //     .subscribe((data: Fridge[]) => {
  //       that.fridgeDB = data;
  //     });
  // }

  call5(ingredient, quantite) {
    let blockToAdd = document.createElement("div");
    blockToAdd.className = "row mb-1 text-center";
    blockToAdd.innerHTML = `
      <span class="col-8 border m-auto border bg-white">${ingredient.name}</span>
      <span class="col-8 border m-auto border bg-white">${quantite}</span>`;
    let blockContainer = document.getElementById("ingredients_listing");
    blockContainer.appendChild(blockToAdd);
  }

  call6(id) {
    let that = this;
    this.recipeService.getComments(id)
      .subscribe((data: Comment[]) => {
        that.commentsDB = data;
        that.call7();
      });
  }

  call7() {
    for (var i = 0; i < this.commentsDB.length; i++) {
      let blockToAdd = document.createElement("div");
      blockToAdd.className = "row mb-1 text-center";
      blockToAdd.innerHTML = `<span class="col-8 border m-auto border bg-white">${this.commentsDB[i].commentRecipe}</span>`;
      let blockContainer = document.getElementById("comments_listing");
      blockContainer.appendChild(blockToAdd);
    }
  }

  add_shop_list() {
    let url = this.router.url;
    let split = url.split("recipe/");
    let recipeID = Number(split[1]);
    let userID = this.userID;
    let listItemCart = [];
    for (var i = 0; i < this.ingredientsRecipeDB.length; i++) {
      let ingredientID = this.ingredientsRecipeDB[i].ingredientId;
      let quantity = this.ingredientsRecipeDB[i].quantite;
      let itemCart = new ItemCart(userID, ingredientID, quantity);
      listItemCart.push(itemCart);
    }
    this.listService.addToCart(listItemCart).subscribe();
    let link = "list/" + recipeID;
    this.router.navigate([link]);
  }

  give_grade() {
    if(this.newGradeElem.value !== "") {
      if (this.newGradeElem.value > 5){
        alert("Please enter a number between 0-5");
        return;
      }
      let url = this.router.url;
      let split = url.split("recipe/");
      let recipeID = Number(split[1]);
      let userID = this.userID;
      let that = this;
      let grade = new Grade(recipeID, userID, this.newGradeElem.value);
      this.recipeService.addGrade(grade).subscribe();
      let link = "recipe/" + recipeID;
      this.router.navigate([link]);
    }
  }

  add_comment() {
    if(this.commentElem.value !== null) {
      let url = this.router.url;
      let split = url.split("recipe/");
      let recipeID = Number(split[1]);
      let string = this.commentElem.value;
      let that = this;
      let comment = new Comment(recipeID, string);
      this.recipeService.addComment(comment).subscribe();
      let link = "recipe/" + recipeID;
      this.router.navigate([link]);
    }
  }

  do_it() {
    let url = this.router.url;
    let split = url.split("recipe/");
    let recipeID = Number(split[1]);
    let userID = this.userID;
    this.profileService.addNewPlannedRecipe(userID, recipeID).subscribe();
    let link = "profile/" + userID;
    this.router.navigate([link]);
  }

  handleKeyPress(e) {
    var code = (e.which) ? e.which : e.keyCode;
    let val = e.target.value.split('');
    let countDot = val.filter((v) => (v === '.')).length;
    if (code == 46 && countDot == 0){
      return true;
    }
    if (code > 31 && (code < 48 || code > 57)) {
        e.preventDefault();
    }
  }

}
