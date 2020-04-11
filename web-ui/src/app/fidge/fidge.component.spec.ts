import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FidgeComponent } from './fidge.component';

describe('FidgeComponent', () => {
  let component: FidgeComponent;
  let fixture: ComponentFixture<FidgeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FidgeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FidgeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
