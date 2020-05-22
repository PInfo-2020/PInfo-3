import { NgModule } from '@angular/core';
import {ExtraOptions, Routes, RouterModule } from '@angular/router';
import { FormsModule }   from '@angular/forms';

import {HomeComponent} from './home/home.component';
//import {LoginComponent} from './login/login.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { ProfileComponent } from './profile/profile.component';
import { CreateRecipeComponent } from './create-recipe/create-recipe.component';
import { RecipeComponent } from './recipe/recipe.component';
import { FridgeComponent } from './fridge/fridge.component';
import { ShoppingListComponent } from './shopping-list/shopping-list.component';

import {
  NbAuthComponent,
  NbLoginComponent,
  NbLogoutComponent,
  NbRegisterComponent,
  NbRequestPasswordComponent,
  NbResetPasswordComponent,
} from '@nebular/auth';

const routes: Routes = [
  
	{
		path: '',
		component: HomeComponent
	},
//	{
//		path: 'login',
//		component: LoginComponent
//	},
	{
		path: 'home',
		component: HomeComponent
	},
	{
		path: 'aboutUs',
		component: AboutUsComponent
	},
	{
		path: 'createRecipe',
		component: CreateRecipeComponent
	},
	{
		path: 'recipe',
		component: RecipeComponent
	},
	{
		path: 'fridge',
		component: FridgeComponent
	},
	{
		path: 'profile',
		component: ProfileComponent
	},
	{
		path: 'shoppingList',
		component: ShoppingListComponent
	},
	{
    path: 'auth',
    component: NbAuthComponent,
    children: [
      {
        path: '',
        component: NbLoginComponent,
      },
      {
        path: 'login',
        component: NbLoginComponent,
      },
      {
        path: 'register',
        component: NbRegisterComponent,
      },
      {
        path: 'logout',
        component: NbLogoutComponent,
      },
      {
        path: 'request-password',
        component: NbRequestPasswordComponent,
      },
      {
        path: 'reset-password',
        component: NbResetPasswordComponent,
      },
    ],
  },
	
	
];

const config: ExtraOptions = {
  useHash: true,
};

@NgModule({
  imports: [RouterModule.forRoot(routes), FormsModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
