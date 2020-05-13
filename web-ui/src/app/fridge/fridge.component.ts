import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-fridge',
  templateUrl: './fridge.component.html',
  styleUrls: ['./fridge.component.css']
})
export class FridgeComponent implements OnInit {

  constructor() { }
  
  ngOnInit(): void {
  }
  add(){
    alert("Hello");
}
}

