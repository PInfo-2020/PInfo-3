import { Component, OnInit, AfterViewInit} from '@angular/core';
import { KeycloakService } from '../services/keycloak/keycloak.service';
import { KeycloakInstance } from 'keycloak-js';
import { HttpClient } from "@angular/common/http";
import { environment } from '../../environments/environment';
import * as $ from 'jquery';

import { RecipeService } from './recipeService';
import { Recipe } from './recipe';
// import { readSync } from 'fs';
// import { AnyARecord } from 'dns';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, AfterViewInit {

  userID: string = this.keycloak.getKeycloakId();
  recipe: Array<Recipe> = [];
  recipeName: Array<String> = [];
  recipeChose: any;
  dataRecipe: any = [];
  public keycloakAuth: KeycloakInstance;
  private data:any = []
  private urlTop:string = `${environment.recipeService.url}/top`
  private urlVegan:string = `${environment.recipeService.url}/vegetarien`
  private urlVeg:string = `${environment.recipeService.url}/vegetarien`
  private urlFrigo:string = `${environment.recipeService.url}/user/${this.userID}/fridge/recipe`
  private urlBestCooker:string = `${environment.profilesService.url}/getBestCooker`
  private urlRecipe:string = `${environment.recipeService.url}`

  private bestCooker:any = []

  constructor(public keycloak: KeycloakService, private http: HttpClient, public recipeService: RecipeService) { }

   ngOnInit() {
      this.keycloakAuth = this.keycloak.getKeycloakAuth();
      if (this.keycloak.isLoggedIn() === false) {
          this.keycloak.login();
      }


      //only have one checkbox picked
      $('.sev_check').click(function() {
        $('.sev_check').not(this).prop('checked', false);
      });
      this.getRecipe()
  }
  ngAfterViewInit(){
    this.bestCook()
    let recipeDataElem = document.getElementById("recipe-choices");
    let that = this;
    
    this.recipeService.getRecipe()
    .subscribe((data: Recipe[]) => {
      this.recipe = data;

      this.recipe.forEach(function(item){
        var option = document.createElement("option");
        option.value = item.name;
        that.recipeName.push(item.name);
        recipeDataElem.appendChild(option);
      });
    });
  }

  getRecipe(){
    this.http.get(this.urlRecipe).subscribe((res)=>{
      this.dataRecipe = res
    })
  }


  chooseRecipe(){
    this.recipeChose = document.getElementById("recipe");
    this.recipeChose = this.recipeChose.value;
    let res = []

    for(let i=0; i<this.dataRecipe.length; i++){
      if(this.dataRecipe[i].name == this.recipeChose)
        res = this.dataRecipe[i]
    }
    this.placeRecipes([res])
  }


  retrieveAndDisplayRecipes(url: string){
    console.log("in here")
    this.http.get(url).subscribe((res)=>{
      this.data = res
      this.placeRecipes(this.data)
    })
  }

  check1(){
    const url = `${environment.recipeService.url}/top`
    console.log("in here")
    this.http.get(url).subscribe((res)=>{
      this.data = res
      console.log(this.data)
      this.placeRecipes(this.data)
    })
  }

  top(){
    console.log(event);
    console.log("in here")
    this.http.get(this.urlTop).subscribe((res)=>{
      this.data = res
      console.log(this.data)
      this.placeRecipes(this.data)
    })
 }

 vegan(){
  console.log(event);
  console.log("in here")
  this.http.get(this.urlVegan).subscribe((res)=>{
    this.data = res
    console.log(this.data)
    this.placeRecipes(this.data)
  })
}

vege(){
  console.log(event);
  console.log("in here")
  this.http.get(this.urlVeg).subscribe((res)=>{
    this.data = res
    console.log(this.data)
    this.placeRecipes(this.data)
  })
}

frigo(){
  console.log(event);
  console.log("in here")
  this.http.get(this.urlFrigo).subscribe((res)=>{
    this.data = res
    console.log(this.data)
    this.placeRecipes(this.data)
  })
}

bestCook(){
  this.http.get(this.urlBestCooker).subscribe((res)=>{
    this.bestCooker = res
    this.placeBestCooker(this.bestCooker)
  })
}

placeBestCooker(data){
  var mainContainer = document.getElementById("bestCook");
  while (mainContainer.firstChild) {
    mainContainer.firstChild.remove();
  }
  for (var i = 0; i < data.length; i++) {
    //creating the card of one recipe
    var cardCooker = document.createElement("div");
    cardCooker.className = "card";
    //creating card body
    var cardBody = document.createElement("div");
    cardBody.className = "card-body";
    //creating title
    var cardTitle = document.createElement("h5");
    cardTitle.className = "card-text";
    cardTitle.innerHTML = (data[i].username);
    //creating text
    var cardText = document.createElement("h6");
    cardText.className = "card-body";
    cardText.innerHTML = (data[i].score);
    //creating card link
    var cardLink = document.createElement("a");
    cardLink.className = "card-link";
    cardLink.innerHTML = "More...";
    cardLink.href = environment.angular.url + "/profile" + "/" + data[i].usernameID;
    //this._elem.nativeElement.innerHTML = <a class='ml-auto text-dark mr-5' routerLink="/createRecipe" routerLinkActive="active">Create recipe</a>
    //put the title and text into the body
    cardBody.appendChild(cardTitle);
    cardBody.appendChild(cardText);
    cardBody.appendChild(cardLink);
    cardCooker.appendChild(cardBody);
    mainContainer.appendChild(cardCooker);
  }
}

  placeRecipes(data){
    var mainContainer = document.getElementById("myData");
    while (mainContainer.firstChild) {
      mainContainer.firstChild.remove();
    }
    console.log(data.length)

    for (var i = 0; i < data.length; i++) {

      //creating the card of one recipe
      var cardRecipe = document.createElement("div");
      cardRecipe.className = "card";
      //creating card body
      var cardBody = document.createElement("div");
      cardBody.className = "card-body";
      //creating title
      var cardTitle = document.createElement("h5");
      cardTitle.className = "card-text";
      cardTitle.innerHTML = (data[i].name);
      //creating text
      var cardText = document.createElement("h6");
      cardText.className = "card-body";
      cardText.innerHTML = (data[i].description);
      //creating card link
      var cardLink = document.createElement("a");
      cardLink.className = "card-link";
      cardLink.innerHTML = "More...";
      cardLink.href = environment.angular.url + "/recipe" + "/" + data[i].id;
      //this._elem.nativeElement.innerHTML = <a class='ml-auto text-dark mr-5' routerLink="/createRecipe" routerLinkActive="active">Create recipe</a>

      //put the title and text into the body
      cardBody.appendChild(cardTitle);
      cardBody.appendChild(cardText);
      cardBody.appendChild(cardLink);


      cardRecipe.appendChild(cardBody);
      mainContainer.appendChild(cardRecipe);

    }
  }

 }
