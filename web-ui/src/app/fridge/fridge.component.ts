import { Component, OnInit } from '@angular/core';
import { HIGH_CONTRAST_MODE_ACTIVE_CSS_CLASS } from '@angular/cdk/a11y/high-contrast-mode/high-contrast-mode-detector';
import {KeycloakService} from '../services/keycloak/keycloak.service';
import {KeycloakInstance} from 'keycloak-js';
import * as $ from 'jquery';
import { Ingredient } from './ingredient.component';

@Component({
  selector: 'app-fridge',
  templateUrl: './fridge.component.html',
  styleUrls: ['./fridge.component.css']
})
export class FridgeComponent implements OnInit {
  
  public keycloakAuth: KeycloakInstance;
  constructor(public keycloak: KeycloakService){}
  
  ngOnInit(): void {
    this.keycloakAuth = this.keycloak.getKeycloakAuth();
    if(this.keycloak.isLoggedIn() === false){
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
        
          Nom:<div class="col-2 border border-w m-auto" id= ${idIng}>${ingredient}</div>
      
          Quantité:<div class="col-4 border m-auto" id= ${idQuantity}>${quantity}</div>
          
          <button type="button" class="btn btn-light" (click)="plus()">+</button>
      
          <button type="button" class="btn btn-light" (click)="minus()">-</button>

          <button type="button" class="btn btn-light" ">x</button>
        
          `;

        let blockContainer = document.getElementById("div1");
        blockContainer.appendChild(blockToAdd);
        this.ingredients.push(new Ingredient(ingredient, quantity))
        console.log(this.ingredients);
      }
    }
  }
}

