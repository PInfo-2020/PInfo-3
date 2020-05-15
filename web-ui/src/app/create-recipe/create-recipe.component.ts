import { Component, OnInit } from '@angular/core';
import {KeycloakService} from '../services/keycloak/keycloak.service';
import {KeycloakInstance} from 'keycloak-js';

@Component({
  selector: 'app-create-recipe',
  templateUrl: './create-recipe.component.html',
  styleUrls: ['./create-recipe.component.css'],
})
export class CreateRecipeComponent implements OnInit {
  public keycloakAuth: KeycloakInstance;
  constructor(public keycloak: KeycloakService){}

  ngOnInit(): void {
    this,this.keycloakAuth = this.keycloak.getKeycloakAuth();
    if(this.keycloak.isLoggedIn() == false){
      this.keycloak.login();
    }
  }

  plus(){
    let quantity = document.getElementById("quantity");
    quantity.value++;
    }

  minus(){
    let quantity = document.getElementById("quantity");
    if (quantity.value > 0) {
      quantity.value--;
    }
  }

  addBlock1() {
    let ingredient = document.getElementById("ingredient").value;
    let quantity = document.getElementById("quantity").value;

    if (ingredient && quantity) {
      let idIng = "ingredient-".concat(ingredient);
      if (document.getElementById(idIng)) {
        let idQuan = ingredient.concat("-quantity");
        let textnode = document.createTextNode(quantity);
        idQuan.textContent = quantity;
      }
      else {
        let blockToAdd = document.createElement("div");
        blockToAdd.className = "row mb-1";
        blockToAdd.innerHTML = "<div class=\"col border w-50 text-center\" id=\"ingredient-" + ingredient + "\">" + ingredient + "</div><div class=\"col border w-50 text-center\" id=" + ingredient + "-quantity" + "\">" + quantity + "</div>";

        let blockContainer = document.getElementById("div1");
        blockContainer.appendChild(blockToAdd);
      }
    }
  }
}
