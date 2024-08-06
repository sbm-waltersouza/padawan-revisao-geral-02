import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CanalFormComponent } from './canal-form.component';

describe('CanalFormComponent', () => {
  let component: CanalFormComponent;
  let fixture: ComponentFixture<CanalFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CanalFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CanalFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
