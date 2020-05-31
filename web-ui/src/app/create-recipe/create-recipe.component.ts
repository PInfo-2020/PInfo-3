import { Component, OnInit, AfterViewInit } from '@angular/core';
import { KeycloakService } from '../services/keycloak/keycloak.service';
import { KeycloakInstance } from 'keycloak-js';
import { Router } from '@angular/router';
import { IngredientService } from './ingredientService';
import { RecipeService } from './recipeService';
import { Ingredient } from './ingredient';
import { Recipe } from './recipe';
import { IngredientRecipe } from './ingredientRecipe';

@Component({
  selector: 'app-create-recipe',
  templateUrl: './create-recipe.component.html',
  styleUrls: ['./create-recipe.component.css'],
})
export class CreateRecipeComponent implements OnInit, AfterViewInit {
  ingredientsDB: Array<Ingredient> = [];
  ingredientsName: Array<String> = [];
  counterInstruction: number = 1;

  quantityElem: any;
  ingredientElem: any;
  instructionElem: any;
  nameElem: any;
  descriptionElem: any;
  timeElem: any;
  peopleElem: any;
  unitElem: any;


  public keycloakAuth: KeycloakInstance;

  constructor(public keycloak: KeycloakService, private ingredientService: IngredientService, private recipeService: RecipeService, private router: Router){}

  ngOnInit(): void {
    // this.keycloakAuth = this.keycloak.getKeycloakAuth();
    // if (this.keycloak.isLoggedIn() === false) {
    //     this.keycloak.login();
    // }
  }

  ngAfterViewInit() {
    this.quantityElem = document.getElementById("quantity");
    this.ingredientElem = document.getElementById("ingredient");
    this.instructionElem = document.getElementById("instruction");
    this.nameElem = document.getElementById("name");
    this.descriptionElem = document.getElementById("description");
    this.timeElem = document.getElementById("time");
    this.peopleElem = document.getElementById("people");
    this.unitElem = document.getElementById("unit");

    let ingredientDataElem = document.getElementById("ingredients-choices");
    let that = this;

    this.ingredientService.getIngredients()
      .subscribe((data: Ingredient[]) => {
        this.ingredientsDB = data;

        this.ingredientsDB.forEach(function(item){
          let option = document.createElement("option");
          option.value = item.name;
          that.ingredientsName.push(item.name);
          ingredientDataElem.appendChild(option);
        });
      });
  }

  plus(){
    this.quantityElem.value++;
  }

  minus(){
    if (this.quantityElem.value > 0) {
      this.quantityElem.value--;
    }
  }

  addBlock1() {
    let ingredientVal = this.ingredientElem.value;
    let quantityVal = this.quantityElem.value;
    let unitVal = this.unitElem.value;

    if (this.ingredientsName.includes(ingredientVal) && (quantityVal>0)) {
      let index = this.ingredientsName.indexOf(ingredientVal);
      let ingredientValID = (this.ingredientsDB[index].id).toString();

      let idIng = "ingredient-".concat(ingredientValID);
      if (!document.getElementById(idIng)) {
        let blockToAdd = document.createElement("div");
        blockToAdd.className = "row mb-1 text-center";
        blockToAdd.innerHTML = `
          <span class="m-auto border bg-white pl-2 pr-2">Name:</span><span class="col-3 border m-auto bg-white" id=${idIng}>${ingredientVal}</span>
          <span class="m-auto border bg-white pl-2 pr-2">Quantity:</span><span class="col-1 border m-auto bg-white">${quantityVal}</span>
          <span class="m-auto border bg-white pl-2 pr-2">Unit:</span><span class="col-1 border m-auto bg-white">${unitVal}</span>
          <button type="button" class="btn btn-secondary mr-1 button-w" onclick="this.parentNode.remove();">x</button>
          `;

        let blockContainer = document.getElementById("div1");
        blockContainer.appendChild(blockToAdd);
      }
    }
  }

  addBlock2() {
    let instructionVal = this.instructionElem.value;

    if (instructionVal) {
      let blockToAdd = document.createElement("div");
      blockToAdd.className = "row mb-1 text-center";
      blockToAdd.innerHTML = `
        <span class="m-auto font-weight-bold bg-white pl-2 pr-2 border">${this.counterInstruction}.</span><span class="col-8 border m-auto border bg-white">${instructionVal}</span>
        <button type="button" class="btn btn-secondary mr-1 button-w" onclick="this.parentNode.remove();">x</button>
      `;

      let blockContainer = document.getElementById("div2");
      blockContainer.appendChild(blockToAdd);
      this.counterInstruction++;
    }
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

  addUnit(e){
    let ingredientVal = e.target.value;
    if (this.ingredientsName.includes(ingredientVal)) {
      let index = this.ingredientsName.indexOf(ingredientVal)+1;
      this.unitElem.value = this.ingredientsDB[index].unit;
    }
    return true;
  }

  checkData(){
    let unitVal = this.unitElem.value;
    let peopleVal = this.peopleElem.value;
    let nameVal = this.nameElem.value;
    let descriptionVal = this.descriptionElem.value;
    let quantityVal = this.quantityElem.value;
    let instructionVal;
    let ingredientVal;
    if (document.getElementById("div1").children.length > 1){
      ingredientVal = true;
    }
    if (document.getElementById("div2").children.length > 1){
      instructionVal = true;
    }

    if (unitVal && peopleVal && nameVal && descriptionVal && quantityVal && ingredientVal && instructionVal){
      return true;
    }
    return false;
  }

  sendData() {
    if (this.checkData()) {
      let userID = "1";
      let nameVal = this.nameElem.value;
      let descriptionVal = this.descriptionElem.value;
      let timeVal = this.timeElem.value;
      let peopleVal = this.peopleElem.value;
      let instructionVal = "";

      let instructionChilds = document.getElementById("div2").children;
      for (let i = 1; i < instructionChilds.length; i++){
        instructionVal = instructionVal.concat(instructionChilds[i].children[1].innerHTML, "///");
      }

      let recipe = new Recipe(nameVal, descriptionVal, instructionVal, timeVal, peopleVal, userID);
      this.recipeService.sendRecipe(recipe)
        .subscribe((data: number) => {
          this.sendIngredients(data);
        });

      setTimeout(() => {
          this.router.navigate(['home']);
      }, 3000);
      alert("Recipe created ! \n You will be redirected to the home page...");
    }
    else {
      alert("Fill all fields")
    }
  }

  sendIngredients(recipeID){
    let ingredientsRecipe = []
    let ingredientsChilds = document.getElementById("div1").children;
    for (let i = 1; i < ingredientsChilds.length; i++){
      let ingredientRecipeName = ingredientsChilds[i].children[1].innerHTML;
      let ingredientRecipeQuantity = Number(ingredientsChilds[i].children[3].innerHTML);
      let ingredientRecipe = this.ingredientsDB.find(i => i.name === ingredientRecipeName);
      let ingredientRecipeId = ingredientRecipe.id;
      let ingredientRecipeVegetarian = ingredientRecipe.vegetarian;
      let ingredientRecipeVegan = ingredientRecipe.vegan;

      ingredientsRecipe.push(new IngredientRecipe(ingredientRecipeId, ingredientRecipeQuantity, ingredientRecipeVegetarian, ingredientRecipeVegan))
    }
    this.recipeService.sendIngredientsRecipe(ingredientsRecipe, recipeID)
      .subscribe();
  }

}
