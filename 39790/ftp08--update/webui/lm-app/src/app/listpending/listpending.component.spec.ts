import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListpendingComponent } from './listpending.component';

describe('ListpendingComponent', () => {
  let component: ListpendingComponent;
  let fixture: ComponentFixture<ListpendingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListpendingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListpendingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
