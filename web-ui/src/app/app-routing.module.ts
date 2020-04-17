import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from './home/home.component';
import {LoginComponent} from './login/login.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { ProfileComponent } from './profile/profile.component';
import { CreateRecipeComponent } from './create-recipe/create-recipe.component';
import { RecipeComponent } from './recipe/recipe.component';
import { FridgeComponent } from './fridge/fridge.component';
import { ShoppingListComponent } from './shopping-list/shopping-list.component';

const routes: Routes = [
	{
		path: '',
		component: LoginComponent
	},
	{
		path: 'login',
		component: LoginComponent
	},
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
		path: 'shoppinfList',
		component: ShoppingListComponent
	}
	
	
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
