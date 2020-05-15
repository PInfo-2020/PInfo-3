import { Component, OnInit } from '@angular/core';
import { HIGH_CONTRAST_MODE_ACTIVE_CSS_CLASS } from '@angular/cdk/a11y/high-contrast-mode/high-contrast-mode-detector';
import {KeycloakService} from '../services/keycloak/keycloak.service';
import {KeycloakInstance} from 'keycloak-js';

@Component({
  selector: 'app-fridge',
  templateUrl: './fridge.component.html',
  styleUrls: ['./fridge.component.css']
})
export class FridgeComponent implements OnInit {
  ingredient = 'poulet';
  quantity = '';
  unit = '';
  
  public keycloakAuth: KeycloakInstance;
  constructor(public keycloak: KeycloakService){}
  
  ngOnInit(): void {
    this,this.keycloakAuth = this.keycloak.getKeycloakAuth();
    if(this.keycloak.isLoggedIn() == false){
      this.keycloak.login();
    }
  }
  getIngredient(){
    return this.ingredient;
  }
  getQuantity(){
    return this.quantity;
  }
  getUnit(){
    return this.unit;
  }
  onAdd(){
    console.log("Hello");
    }
}

