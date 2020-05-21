import { Component, OnInit } from '@angular/core';
import { KeycloakService } from '../services/keycloak/keycloak.service';
import { KeycloakInstance } from 'keycloak-js';
import { HttpClient } from "@angular/common/http";
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public keycloakAuth: KeycloakInstance;
  private data:any = []

  constructor(public keycloak: KeycloakService, private http: HttpClient) { }

  ngOnInit(): void {
      console.log("her4")
      this.keycloakAuth = this.keycloak.getKeycloakAuth();
      console.log("her3")
      console.log("her8", this.keycloak)
      /*if (this.keycloak.isLoggedIn() === false) {
        console.log("her1")
          this.keycloak.login();
          console.log("her2")
      }*/

      console.log("in here")
      const url = environment.recipeService.url + "/top"
      console.log("after")
      this.http.get(url).subscribe((res)=>{
        this.data = res
        console.log(this.data)
        this.displayBestRecipes(this.data)
  
      })


  }

  displayBestRecipes(data){
    var mainContainer = document.getElementById("myData");
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
  getTopRecipes(){
    console.log("in here")
    const url = environment.recipeService.url + "/top"
    this.http.get(url).subscribe((res)=>{
      this.data = res
      console.log(this.data)
    })
  }
 }

 



