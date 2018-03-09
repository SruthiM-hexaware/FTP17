import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MyManagerComponent } from './my-manager.component';

describe('MyManagerComponent', () => {
  let component: MyManagerComponent;
  let fixture: ComponentFixture<MyManagerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MyManagerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
