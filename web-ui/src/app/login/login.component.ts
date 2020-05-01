import { Component, OnInit } from '@angular/core';
import {Loggin} from '../loggin';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']


})
export class LoginComponent implements OnInit {
	loggin: Loggin = {
		username: '',
		password: ''
	}
  constructor() { }

  ngOnInit(): void {
  }

}
