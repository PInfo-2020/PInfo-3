import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from './user';
import { PlannedRecipe } from './plannedRecipe';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ProfileService {
  constructor(private http: HttpClient) { }

  httpOptions = {
       headers: new HttpHeaders({
           'Content-Type': 'application/json',
       }),
   };

  getUser(id: String): Observable<User> {
    return this.http.get<User>(environment.profilesService.url + "/" + id)
      .pipe(
                retry(1),
                catchError(this.handleError),
            );
  }

  getPlannedRecipe(id: String): Observable<PlannedRecipe[]> {
    return this.http.get<PlannedRecipe[]>(environment.profilesService.url + "/plannedrecipes/" + id)
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
