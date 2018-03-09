import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ApprovedenyComponent } from './approvedeny.component';

describe('ApprovedenyComponent', () => {
  let component: ApprovedenyComponent;
  let fixture: ComponentFixture<ApprovedenyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ApprovedenyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ApprovedenyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
