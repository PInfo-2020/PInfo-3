import { Component, OnInit } from '@angular/core';
import { KeycloakService } from '../services/keycloak/keycloak.service';
import { KeycloakInstance } from 'keycloak-js';
import { HttpClient } from "@angular/common/http";
import { environment } from '../../environments/environment';
import * as $ from 'jquery';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public keycloakAuth: KeycloakInstance;
  private data:any = []
  private urlTop:string = `${environment.recipeService.url}/top`
  private urlVegan:string = `${environment.recipeService.url}/vegetarien`
  private urlVeg:string = `${environment.recipeService.url}/vegetarien`
  private urlFrigo:string = `${environment.recipeService.url}/user/1/fridge/recipe`/*fix me! with the right id!*/

  userID: string = this.keycloak.getKeycloakId();

  constructor(public keycloak: KeycloakService, private http: HttpClient) { }

   ngOnInit() {
      this.keycloakAuth = this.keycloak.getKeycloakAuth();
      if (this.keycloak.isLoggedIn() === false) {
          this.keycloak.login();
      }


      //only have one checkbox picked
      $('.sev_check').click(function() {
        $('.sev_check').not(this).prop('checked', false);
      });

  }

  retrieveAndDisplayRecipes(url: string){
    console.log("in here")
    this.http.get(url).subscribe((res)=>{
      this.data = res
      console.log(this.data)
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

  placeRecipes(data){
    var mainContainer = document.getElementById("myData");
    while (mainContainer.firstChild) {
      mainContainer.firstChild.remove();
    }
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
      cardLink.href = environment.angular.url + "/recipe";
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
