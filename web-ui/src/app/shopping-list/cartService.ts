import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Cart } from './cart';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { environment } from '../../environments/environment';

@Injectable({
    providedIn: 'root',
  })

export class CartService {
    constructor(private http: HttpClient) { }
  
    httpOptions = {
         headers: new HttpHeaders({
             'Content-Type': 'application/json',
         }),
     };


sendIngredientsCart(cart: Cart){
    return this.http.post(environment.listsService.url + "/addtocart", cart)
        .pipe(
            retry(1),
            catchError(this.handleError)
        );
}

deleteIngredientCart(cart: Cart){
    return this.http.post(environment.listsService.url + "/removefromcart", cart)
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