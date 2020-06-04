import { HIGH_CONTRAST_MODE_ACTIVE_CSS_CLASS } from '@angular/cdk/a11y/high-contrast-mode/high-contrast-mode-detector';
// import {KeycloakService} from '../services/keycloak/keycloak.service';
// import {KeycloakInstance} from 'keycloak-js';
import { Component, OnInit, AfterViewInit } from '@angular/core';

import * as $ from 'jquery';
import { IngredientService } from './ingredientService';
import { Ingredient } from './ingredient';
import { Cart } from './cart';
import { CartService } from './cartService';
import { environment } from '../../environments/environment';
import { HttpClient } from "@angular/common/http";
import { Router, ActivatedRoute } from '@angular/router';



@Component({
  selector: 'app-shopping-list',
  templateUrl: './shopping-list.component.html',
  styleUrls: ['./shopping-list.component.css']
})
export class ShoppingListComponent implements OnInit, AfterViewInit {

  ingredientsDB: Array<Ingredient> = [];
  ingredientsName: Array<String> = [];

  quantityElem: any;
  unitElem: any;
  ingredientElem: any;
  id: any;
  urlShopping:string;
  urlIngredient:string = `${environment.ingredientsService.url}`;

  dataCart:any = [];
  dataIngredient:any = [];
  ingredientID: number;
  

  // public keycloakAuth: KeycloakInstance;

  // constructor(public keycloak: KeycloakService, public ingredientService: IngredientService){}
  constructor(public ingredientService: IngredientService, private http: HttpClient, private cartService : CartService, private router: Router, private route: ActivatedRoute){}


  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.id = params['id'];        
      console.log(this.id);
    });
  }

  ngAfterViewInit(){
    //this.getFridge();
    this.urlShopping = `${environment.listsService.url}/cart/${this.id}`;
    this.getIngredientsList();
    
    this.quantityElem = document.getElementById("quantity");
    this.ingredientElem = document.getElementById("ingredient");
    this.unitElem = document.getElementById("unit");

    let ingredientDataElem = document.getElementById("ingredients-choices");
    let that = this;

    this.ingredientService.getIngredients()
    .subscribe((data: Ingredient[]) => {
      this.ingredientsDB = data;

      this.ingredientsDB.forEach(function(item){
        var option = document.createElement("option");
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
  getCart(){
    this.http.get(this.urlShopping).subscribe((res)=>{
      this.dataCart = res
      this.addCart()
    })
  }

  getIngredientsList(){
    this.http.get(this.urlIngredient).subscribe((res)=>{
      this.dataIngredient = res
      this.getCart()
    })
  }

  addCart(){
    var mainContainer = document.getElementById("myData")
    while (mainContainer.firstChild) {
      mainContainer.firstChild.remove();
    }
    for (let i=0; i<this.dataCart.length; i++){
      let ingredientId = this.dataCart[i].ingredientID;
      let ingredientName = " ";
      let unitVal = " "
      for(let j=0; j<this.dataIngredient.length; j++){
        if(ingredientId == this.dataIngredient[j].id){
          ingredientName = this.dataIngredient[j].name;
          unitVal = this.dataIngredient[j].unit;
        }
      }

      let blockToAdd = document.createElement("div");
      blockToAdd.className = "row mb-1 text-center";
      blockToAdd.innerHTML = `
        <span class="m-auto border bg-white pl-2 pr-2">Name:</span><span class="col-3 border m-auto bg-white" id="ingredient">${ingredientName}</span>
        <span class="m-auto border bg-white pl-2 pr-2">Quantity:</span><span class="col-1 border m-auto bg-white" id="quantity">${this.dataCart[i].quantity}</span>
        <span class="m-auto border bg-white pl-2 pr-2">Unit:</span><span class="col-1 border m-auto bg-white">${unitVal}</span>
        <button id="${[this.dataCart[i].ingredientID, this.dataCart[i].quantity]}" type="button" class="btn btn-secondary mr-1 button-w">x</button>
      `;

      let blockContainer = document.getElementById("div2");
      blockContainer.appendChild(blockToAdd);

      // var contrain = document.createElement("div");
      // var contrainFridge = document.createElement("div");
      // contrainFridge.className = "frigo";
      // var ingre = document.createElement("h5");
      // ingre.innerHTML = "Ingredient :" + (ingredientName) + " Quantity :" + (this.dataCart[i].quantity) + `<button type="button" class="btn btn-secondary mr-1 button-w" onclick="this.removeIngredient(this.dataCart[i].ingredientID, this.dataCart[i].quantity)">x</button>`;

      // // var quan = document.createElement("h5");
      // // ingre.innerHTML = "Quantity " + (dataCart[i].quantity);

      // contrainFridge.appendChild(ingre);
      // // contrainFridge.appendChild(quan);
      // contrain.appendChild(contrainFridge)
      // mainContainer.appendChild(contrain);
    }

    // document.addEventListener('click', event => {
    //   var target = event.target || event.srcElement || event.currentTarget;
    //   var idAttr = (target as Element).id;
    //   var value = idAttr.split(",");
    //   this.removeIngredient(+value[0], +value[1]);
    // });
  }
  // removeIngredient(ingredientToDeleteID: number, quantityToDelete: number){
  //   let userId = this.id;
  //   console.log(ingredientToDeleteID)
  //   console.log(quantityToDelete)
  //   console.log(typeof this.id)
  //   let fridge = new Fridge(userId, ingredientToDeleteID, quantityToDelete);
  //   this.fridgeService.deleteIngredientFridge(fridge)
  //     .subscribe();
  // }

  sendData(){
    let quantityVal = document.getElementById("div1").children;
    let userId = this.id;
    let ingredient = document.getElementById("div1").children;
    for(let i=0; i<ingredient.length; i++){
      let ingredientName = ingredient[i].children[1].innerHTML; 
      let ingredientQuantity = quantityVal[i].children[3].innerHTML;
      for(let j=0; j<this.dataIngredient.length; j++){
        if(ingredientName == this.dataIngredient[j].name){
          let idIngredient = this.dataIngredient[j].id;
          let cart = new Cart(userId, idIngredient, ingredientQuantity);
          this.cartService.sendIngredientsCart(cart)
            .subscribe();
        }

      }
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

  addUnit(e){
    let ingredientVal = e.target.value;
    if (this.ingredientsName.includes(ingredientVal)) {
      let index = this.ingredientsName.indexOf(ingredientVal)+1;
      this.unitElem.value = this.ingredientsDB[index].unit;
    }
    return true;
  }

  addCartToFridge(){
    this.http.get(this.urlShopping).subscribe((res)=>{
      this.dataCart = res
    })
    console.log(this.dataCart)

    for(let i=0; i<this.dataCart.length; i++){
      let cart = new Cart(this.id, this.dataCart[i].ingredientID, this.dataCart[i].quantity);
      this.cartService.sendIngredientsFridge(cart)
        .subscribe();
    }
    let cart = new Cart(this.id, this.dataCart[1].ingredientID, this.dataCart[1].quantity);
      this.cartService.deleteIngredientCart(cart)
        .subscribe();
    console.log(cart)
    
    // for(let i=0; i<this.dataCart.length; i++){
    //   let cart = new Cart(this.id, this.dataCart[i].ingredientID, this.dataCart[i].quantity);
    //   console.log(cart)
    //   this.cartService.deleteIngredientCart(cart)
    //     .subscribe();
    // }
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


