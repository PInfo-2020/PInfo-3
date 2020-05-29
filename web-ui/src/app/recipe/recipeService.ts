import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Recipe } from './recipe';
import { IngredientRecipe } from './ingredientRecipe'
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class RecipeService {
  constructor(private http: HttpClient) { }

  httpOptions = {
       headers: new HttpHeaders({
           'Content-Type': 'application/json',
       }),
   };

  getRecipe(id: Number): Observable<Recipe> {
     return this.http.get<Recipe>(environment.recipeService.url + "/" + id)
       .pipe(
                 retry(1),
                 catchError(this.handleError),
             );
  }

  getGrade(id: Number): Observable<Number> {
     return this.http.get<Number>(environment.recipeService.url + "/" + id + "/grade")
       .pipe(
                 retry(1),
                 catchError(this.handleError),
             );
  }

  getIngredientsRecipe(id: Number): Observable<IngredientRecipe[]> {
    return this.http.get<IngredientRecipe[]>(environment.recipeService.url + "/" + id + "/ingredient")
      .pipe(
                retry(1),
                catchError(this.handleError),
            );
  }

  handleError(error) {
        let errorMessage = '';
        if (error.error instanceof ErrorEvent) {
            // Get client-side error
            errorMessage = error.error.message;
        } else {
            // Get server-side error
            errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
        }
        console.error(errorMessage);
        return throwError(errorMessage);
  }
}
