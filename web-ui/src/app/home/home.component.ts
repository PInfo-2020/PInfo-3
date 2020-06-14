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
import { catchError, retry } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { Router } from '@angular/router';

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

  constructor(public keycloak: KeycloakService, private http: HttpClient, public recipeService: RecipeService, public router: Router) { }

  newuser(username, idUser) {
    console.log(environment.profilesService.url + "/"+idUser+"/" + username + "/addNewUser")
    let url = environment.profilesService.url + "/"+idUser+"/" + username + "/addNewUser"
    //this.http.post(environment.profilesService.url + "/"+idUser+"/" + username + "/addNewUser", {})


    this.http.post(url,  []).
    subscribe(
      data => {
        console.log('ok user added if not there already');
      },
      error => {
        console.log("error");
      }
    )

  }

   ngOnInit() {
      this.keycloakAuth = this.keycloak.getKeycloakAuth();
      if (this.keycloak.isLoggedIn() === false) {
          this.keycloak.login();
      }
      this.newuser(this.keycloak.getUsername(),this.keycloak.getKeycloakId() );

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
    this.top();
  }

  getRecipe(){
    this.http.get(this.urlRecipe).subscribe((res)=>{
      this.dataRecipe = res
    })
  }


  chooseRecipe(){
    this.recipeChose = document.getElementById("recipe");
    this.recipeChose = this.recipeChose.value;
    if (this.recipeChose != ""){
      let res = []

      for(let i=0; i<this.dataRecipe.length; i++){
        if(this.dataRecipe[i].name == this.recipeChose)
          res.push(this.dataRecipe[i])
      }
      this.placeRecipes(res)
    }
  }


  retrieveAndDisplayRecipes(url: string){
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
    let buttonId = "user".concat(data[i].usernameID);

    let div = document.createElement("div");
    div.className = "row border rounded bg-white mt-1 pt-2 pb-2";
    div.innerHTML = `
      <div class="row w-100 mb-2">
        <div class="col ml-5">${data[i].username}</div>
        <div class="col text-right">${data[i].score}</div>
        <img src="../assets/img/star.png" />
      </div>
      <div class="row w-100">
        <button class="btn btn-primary ml-auto" id=${buttonId}>More...</button>
      </div>
    `;
    mainContainer.appendChild(div);
    let that = this;
    let usernameID = data[i].usernameID;
    $("#".concat(buttonId)).click(function(){
      that.router.navigate(['/profile/', usernameID]);
    });
  }
}

placeRecipes(data){
  var mainContainer = document.getElementById("myData");
  while (mainContainer.firstChild) {
    mainContainer.firstChild.remove();
  }
  let observables = data.map(item => this.http.get(`${environment.recipeService.url}/${item.id}/grade`).subscribe((res)=>{
      let buttonId = "recipe".concat(item.id);
      let div = document.createElement("div");
      div.className = "row border rounded bg-white mt-1 pt-2 pb-2";
      div.innerHTML = `
        <div class="row w-100 mb-2">
          <div class="col-10 text-center ml-2">${item.name}</div>
          <div class="col-1 text-right">${res}</div>
          <img src="../assets/img/star.png" />
        </div>
        <div class="row w-100">
          <div class="ml-5">${item.description}</div>
          <button class="btn btn-primary ml-auto" id=${buttonId}>More...</button>
        </div>
      `;
      mainContainer.appendChild(div);
      let that = this;
      let usernameID = item.id;
      $("#".concat(buttonId)).click(function(){
        that.router.navigate(['/recipe/', item.id]);
      });
    }));
  }

}
