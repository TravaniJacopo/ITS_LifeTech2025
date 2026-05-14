import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Nuovarecensione } from './nuovarecensione';

describe('Nuovarecensione', () => {
  let component: Nuovarecensione;
  let fixture: ComponentFixture<Nuovarecensione>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Nuovarecensione],
    }).compileComponents();

    fixture = TestBed.createComponent(Nuovarecensione);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
