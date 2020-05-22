import { Component, OnInit } from '@angular/core';
import { HIGH_CONTRAST_MODE_ACTIVE_CSS_CLASS } from '@angular/cdk/a11y/high-contrast-mode/high-contrast-mode-detector';
// import {KeycloakService} from '../services/keycloak/keycloak.service';
// import {KeycloakInstance} from 'keycloak-js';
import * as $ from 'jquery';
import { IngredientService } from './ingredientService';
import { Ingredient } from './ingredient';
import { Fridge } from './fridge';
import { IngredientFridge } from './ingredientFridge'
import { FridgeService } from './fridgeService';
import { environment } from '../../environments/environment';
import { HttpClient } from "@angular/common/http";


@Component({
  selector: 'app-fridge',
  templateUrl: './fridge.component.html',
  styleUrls: ['./fridge.component.css']
})
export class FridgeComponent implements OnInit {

  ingredientsDB: Array<Ingredient> = [];
  quantityElem: any;
  ingredientElem: any;
  urlFridge:string = `${environment.listsService.url}/fridge/1`;
  dataFridge:any = []

  // public keycloakAuth: KeycloakInstance;

  // constructor(public keycloak: KeycloakService, public ingredientService: IngredientService){}
  constructor(public ingredientService: IngredientService, private http: HttpClient){}

  ngOnInit(): void {
    // this.keycloakAuth = this.keycloak.getKeycloakAuth();
    // if (this.keycloak.isLoggedIn() === false) {
    //     this.keycloak.login();
    // }
  }

  ngAfterViewInit(){
    this.quantityElem = document.getElementById("quantity");
    this.ingredientElem = document.getElementById("ingredient");

    let ingredientDataElem = document.getElementById("ingredients-choices");

    this.ingredientService.getIngredients()
      .subscribe((data: Ingredient[]) => {
        this.ingredientsDB = data;

        this.ingredientsDB.forEach(function(item){
          var option = document.createElement("option");
          option.value = item.name;
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

  addFridge(dataFridge){
    var mainContainer = document.getElementById("myData")
    while (mainContainer.firstChild) {
      mainContainer.firstChild.remove();
    }
    for (var i=0; i<dataFridge.length; i++){
      var contrain = document.createElement("div");
      var contrainFridge = document.createElement("div");
      contrainFridge.className = "frigo";
      var ingre = document.createElement("h5");
      ingre.innerHTML = "Ingredient " + (dataFridge[i].ingredientID) + " Quantity " + (dataFridge[i].quantity);
      // var quan = document.createElement("h5");
      // ingre.innerHTML = "Quantity " + (dataFridge[i].quantity);

      contrainFridge.appendChild(ingre);
      // contrainFridge.appendChild(quan);
      contrain.appendChild(contrainFridge)
      mainContainer.appendChild(contrain);
    }
  }

  addBlock1() {
    let ingredientVal = this.ingredientElem.value;
    let quantityVal = this.quantityElem.value;
    let ingredientsName = []
    this.ingredientsDB.forEach(function(item){
      ingredientsName.push(item.name);
    });

    if (ingredientsName.includes(ingredientVal) && (quantityVal>0)) {
      let idIng = "ingredient-".concat(ingredientVal);
      if (!document.getElementById(idIng)) {
        let blockToAdd = document.createElement("div");
        blockToAdd.className = "row mb-1 text-center";
        blockToAdd.innerHTML = `
          <span class="m-auto">Name:</span><span class="col-4 border m-auto" id= ${idIng}>${ingredientVal}</span>
          <span class="m-auto">Quantity:</span><span class="col-4 border m-auto">${quantityVal}</span>
          <button type="button" class="btn btn-secondary mr-1 button-w" onclick="this.parentNode.remove();">x</button>
          `;

        let blockContainer = document.getElementById("div1");
        blockContainer.appendChild(blockToAdd);
      }
    }
  }


  handleKeyPress(e) {
    var code = (e.which) ? e.which : e.keyCode;
    let quantityVal = this.quantityElem.value.split('');
    let countDot = quantityVal.filter((v) => (v === '.')).length;
    if (code == 46 && countDot == 0){
      return true;
    }
    if (code > 31 && (code < 48 || code > 57)) {
        e.preventDefault();
    }
  }

  getFridge(){
    this.http.get(this.urlFridge).subscribe((res)=>{
      this.dataFridge = res
      console.log(this.dataFridge)
      this.addFridge(this.dataFridge)
    })
  }

  // sendData() {
  //   let userID = "1";
  //   let ingredientID = [];
  //   let quantity = 1;
  //   let instructionChilds = document.getElementById("div2").children;
    

  //   let fridge = new Fridge(userID, ingredientID, quantity);
  //   this.fridgeService.sendRecipe(fridge)
  //     .subscribe((data: number) => {
  //       this.sendIngredients(data);
  //     });
  // }


  // sendIngredients(recipeID){
  //   let ingredientsRecipe = []
  //   let ingredientsChilds = document.getElementById("div1").children;
  //   for (let i = 1; i < ingredientsChilds.length; i++){
  //     let ingredientRecipeName = ingredientsChilds[i].children[1].innerHTML;
  //     let ingredientRecipeQuantity = Number(ingredientsChilds[i].children[3].innerHTML);
  //     let ingredientRecipe = this.ingredientsDB.find(i => i.name === ingredientRecipeName);
  //     let ingredientRecipeId = ingredientRecipe.id;
  //     let ingredientRecipeVegetarian = ingredientRecipe.vegetarian;
  //     let ingredientRecipeVegan = ingredientRecipe.vegan;

  //     ingredientsRecipe.push(new IngredientRecipe(ingredientRecipeId, ingredientRecipeQuantity, ingredientRecipeVegetarian, ingredientRecipeVegan))
  //   }
  //   this.recipeService.sendIngredientsRecipe(ingredientsRecipe, recipeID)
  //     .subscribe();
  // }
}

