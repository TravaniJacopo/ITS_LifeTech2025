import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChiSono } from './chi-sono';

describe('ChiSono', () => {
  let component: ChiSono;
  let fixture: ComponentFixture<ChiSono>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ChiSono],
    }).compileComponents();

    fixture = TestBed.createComponent(ChiSono);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
