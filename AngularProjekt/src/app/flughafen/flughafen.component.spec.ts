import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FlughafenComponent } from './flughafen.component';

describe('FlughafenComponent', () => {
  let component: FlughafenComponent;
  let fixture: ComponentFixture<FlughafenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FlughafenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FlughafenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
