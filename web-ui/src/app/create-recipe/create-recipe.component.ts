import { Component, OnInit } from '@angular/core';
import { KeycloakService } from '../services/keycloak/keycloak.service';
import { KeycloakInstance } from 'keycloak-js';
import * as $ from 'jquery';
import { Ingredient } from './ingredient.component';

@Component({
  selector: 'app-create-recipe',
  templateUrl: './create-recipe.component.html',
  styleUrls: ['./create-recipe.component.css'],
})
export class CreateRecipeComponent implements OnInit {
  public keycloakAuth: KeycloakInstance;
  constructor(public keycloak: KeycloakService){

  }

  ngOnInit(): void {
    this,this.keycloakAuth = this.keycloak.getKeycloakAuth();
    if(this.keycloak.isLoggedIn() == false){
      this.keycloak.login();
    }
  }

  plus(){
    let quantity: any = document.getElementById("quantity");
    quantity.value++;
  }

  minus(){
    let quantity: any = document.getElementById("quantity");
    if (quantity.value > 0) {
      quantity.value--;
    }
  }

  ingredients: Array<Ingredient> = [];
  addBlock1() {
    let ingredient: any = (document.getElementById("ingredient") as HTMLInputElement).value;
    let quantity: any = (document.getElementById("quantity") as HTMLInputElement).value;

    if (ingredient && quantity) {
      let idIng = "ingredient-".concat(ingredient);
      if (!document.getElementById(idIng)) {
        let idQuantity = ingredient.concat("-quantity");
        let blockToAdd = document.createElement("div");
        let idRow = ingredient.concat("-row");
        blockToAdd.className = "row mb-1 text-center";
        blockToAdd.id = idRow;
        blockToAdd.innerHTML = `
          Nom:<div class="col-4 border m-auto" id= ${idIng}>${ingredient}</div>
          Quantité:<div class="col-4 border m-auto" id= ${idQuantity}>${quantity}</div>
          <!-- <button type="button" class="btn btn-secondary mr-1 button-w" onclick="(function(){let id: any = document.getElementById("poulet-row"); id.remove();})()">X</button> -->
          `;

        let blockContainer = document.getElementById("div1");
        blockContainer.appendChild(blockToAdd);
        this.ingredients.push(new Ingredient(ingredient, quantity))
        console.log(this.ingredients);
      }
    }
  }

  counter: number = 1;
  addBlock2() {
    let description: any = (document.getElementById("descriptionInstruction") as HTMLInputElement).value;

    if (description) {
      let blockToAdd = document.createElement("div");
      blockToAdd.className = "row mb-1 text-center";
      blockToAdd.innerHTML = `
        <span class="m-auto">${this.counter}.</span><div class="col-8 border m-auto border">${description}</div>
      `;

      let blockContainer = document.getElementById("div2");
      blockContainer.appendChild(blockToAdd);
      this.counter++;
    }
  }
}
