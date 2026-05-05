import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaArticoli } from './lista-articoli';

describe('ListaArticoli', () => {
  let component: ListaArticoli;
  let fixture: ComponentFixture<ListaArticoli>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListaArticoli],
    }).compileComponents();

    fixture = TestBed.createComponent(ListaArticoli);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
