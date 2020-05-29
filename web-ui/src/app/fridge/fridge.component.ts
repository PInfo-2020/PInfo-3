import { HIGH_CONTRAST_MODE_ACTIVE_CSS_CLASS } from '@angular/cdk/a11y/high-contrast-mode/high-contrast-mode-detector';
// import {KeycloakService} from '../services/keycloak/keycloak.service';
// import {KeycloakInstance} from 'keycloak-js';
import { Component, OnInit, AfterViewInit } from '@angular/core';

import * as $ from 'jquery';
import { IngredientService } from './ingredientService';
import { Ingredient } from './ingredient';
import { Fridge } from './fridge';
import { IngredientFridge } from './ingredientFridge'
import { FridgeService } from './fridgeService';
import { environment } from '../../environments/environment';
import { HttpClient } from "@angular/common/http";
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-fridge',
  templateUrl: './fridge.component.html',
  styleUrls: ['./fridge.component.css']
})
export class FridgeComponent implements OnInit, AfterViewInit {

  ingredientsDB: Array<Ingredient> = [];
  ingredientsName: Array<String> = [];

  quantityElem: any;
  unitElem: any;
  ingredientElem: any;
  urlFridge:string = `${environment.listsService.url}/fridge/1`;
  urlIngredient:string = `${environment.ingredientsService.url}`;

  dataFridge:any = [];
  dataIngredient:any = [];
  ingredientID: number;
  id: any;


  // public keycloakAuth: KeycloakInstance;

  // constructor(public keycloak: KeycloakService, public ingredientService: IngredientService){}
  constructor(public ingredientService: IngredientService, private http: HttpClient, private fridgeService : FridgeService, private router: Router, private route: ActivatedRoute){}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.id = params['id'];        
      console.log(this.id);
    });
    this.getFridge();
    this.getIngredientsList();
  }

  ngAfterViewInit(){
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
  getFridge(){
    this.http.get(this.urlFridge).subscribe((res)=>{
      this.dataFridge = res
      this.addFridge(this.dataFridge)
    })
  }

  getIngredientsList(){
    this.http.get(this.urlIngredient).subscribe((res)=>{
      this.dataIngredient = res
    })
  }

  // addIngredient(dataIngredient){
  //   console.log(dataIngredient.length);
  //   for(var i=0; i<dataIngredient.length; i++){
  //     console.log(this.dataIngredient[i].id);
  //   }
  // }



  addFridge(dataFridge){
    var mainContainer = document.getElementById("myData")
    while (mainContainer.firstChild) {
      mainContainer.firstChild.remove();
    }
    for (var i=0; i<dataFridge.length; i++){
      let ingredientId = dataFridge[i].ingredientID;
      let ingredientName = "none";
      for(let j=0; j<this.dataIngredient.length; j++){
        if(ingredientId == this.dataIngredient[j].id){
          ingredientName = this.dataIngredient[j].name;
        }
      }

      var contrain = document.createElement("div");
      var contrainFridge = document.createElement("div");
      contrainFridge.className = "frigo";
      var ingre = document.createElement("h5");
      ingre.innerHTML = "Ingredient " + (ingredientName) + " Quantity " + (dataFridge[i].quantity);
      // var quan = document.createElement("h5");
      // ingre.innerHTML = "Quantity " + (dataFridge[i].quantity);

      contrainFridge.appendChild(ingre);
      // contrainFridge.appendChild(quan);
      contrain.appendChild(contrainFridge)
      mainContainer.appendChild(contrain);
    }
  }

  sendData(){
    let quantityVal = document.getElementById("div1").children;
    let userId = this.id;
    let ingredient = document.getElementById("div1").children;

    for(let i=0; i<ingredient.length; i++){
      let ingredientName = ingredient[i].children[1].innerHTML; 
      let ingredientQuantity = quantityVal[i].children[3].innerHTML;
      console.log(ingredientQuantity);

      for(let j=0; j<this.dataIngredient.length; j++){
        if(ingredientName == this.dataIngredient[j].name){
          console.log("SAME");
          let idIngredient = this.dataIngredient[j].id;
          let fridge = new Fridge(userId, idIngredient, ingredientQuantity);
          this.fridgeService.sendIngredientsFridge(fridge)
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

