import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ApproveDummyComponent } from './approve-dummy.component';

describe('ApproveDummyComponent', () => {
  let component: ApproveDummyComponent;
  let fixture: ComponentFixture<ApproveDummyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ApproveDummyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ApproveDummyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
