import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Recipe } from './recipe';
import { Observable, throwError, forkJoin } from 'rxjs';
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

  // return recipe and it's score
  getPlannedRecipe(id: Number): Observable<any[]> {
    let response1 = this.http.get<Recipe>(environment.recipeService.url + "/" + id)
    .pipe(
              retry(1),
              catchError(this.handleError),
          );
    let response2 = this.http.get<Number>(environment.recipeService.url + "/" + id + "/grade")
    .pipe(
              retry(1),
              catchError(this.handleError),
          );
    return forkJoin([response1, response2]);
  }

  // return all recipes from user
  getMyRecipe(id: String): Observable<Recipe[]> {
    return this.http.get<Recipe[]>(environment.recipeService.url + "/user/" + id + "/recipes")
    .pipe(
              retry(1),
              catchError(this.handleError),
          );
  }

  // return score for a recipe
  getMyRecipeScore(id: Number): Observable<Number> {
    return this.http.get<Number>(environment.recipeService.url + "/" + id + "/grade")
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
