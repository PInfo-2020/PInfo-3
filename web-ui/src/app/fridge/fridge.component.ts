import { Component, OnInit } from '@angular/core';
import { HIGH_CONTRAST_MODE_ACTIVE_CSS_CLASS } from '@angular/cdk/a11y/high-contrast-mode/high-contrast-mode-detector';
import {KeycloakService} from '../services/keycloak/keycloak.service';
import {KeycloakInstance} from 'keycloak-js';
import * as $ from 'jquery';
import { IngredientService } from './ingredientService';
import { Ingredient } from './ingredient';

@Component({
  selector: 'app-fridge',
  templateUrl: './fridge.component.html',
  styleUrls: ['./fridge.component.css']
})
export class FridgeComponent implements OnInit {
  
  ingredientsDB: Array<Ingredient> = [];
  quantityElem: any;
  ingredientElem: any;

  public keycloakAuth: KeycloakInstance;

  constructor(public keycloak: KeycloakService, public ingredientService: IngredientService){}

  ngOnInit(): void {
    this.keycloakAuth = this.keycloak.getKeycloakAuth();
    if (this.keycloak.isLoggedIn() === false) {
        this.keycloak.login();
    }
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
          <button type="button" class="btn btn-secondary mr-1 button-w" onclick="plus()">+</button>
          <button type="button" class="btn btn-secondary mr-1 button-w" onclick="minus()">-</button>
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
}

