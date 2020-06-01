import { BrowserModule } from '@angular/platform-browser';
import { APP_INITIALIZER, NgModule} from '@angular/core';

import { FormsModule } from '@angular/forms'; // <-- NgModel lives here
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { ProfileComponent } from './profile/profile.component';
import { CreateRecipeComponent } from './create-recipe/create-recipe.component';
import { RecipeComponent } from './recipe/recipe.component';
import { FridgeComponent } from './fridge/fridge.component';
import { ShoppingListComponent } from './shopping-list/shopping-list.component';

import { APP_BASE_HREF } from '@angular/common';
import { AppInitService } from './app.init';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { KeycloakService } from './services/keycloak/keycloak.service';
import { KeycloakInterceptorService } from './services/keycloak/keycloak.interceptor.service';
declare var window: any;

export function init_config(appLoadService: AppInitService, keycloak: KeycloakService) {
   return () =>  appLoadService.init().then( async () => {
     console.log("Starting project...");
     console.info(window.config);
     await keycloak.init();
     console.log("Finished initialisation")
     },
     );
  }

@NgModule({
  declarations: [
    AppComponent,
//    LoginComponent,
    HomeComponent,
    AboutUsComponent,
    ProfileComponent,
    CreateRecipeComponent,
    RecipeComponent,
    FridgeComponent,
    ShoppingListComponent,
    FridgeComponent
  ],
 imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
  ],
  providers: [
    { provide: APP_BASE_HREF, useValue: '/' },

    AppInitService,
    {
      provide: APP_INITIALIZER,
      useFactory: init_config,
      deps: [AppInitService, KeycloakService],
      multi: true,
    },


    {
      provide: HTTP_INTERCEPTORS,
      useClass: KeycloakInterceptorService,
      multi: true,
    },
    KeycloakService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule { }
