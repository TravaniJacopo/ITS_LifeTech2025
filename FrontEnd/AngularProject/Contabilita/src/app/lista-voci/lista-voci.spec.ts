import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaVoci } from './lista-voci';

describe('ListaVoci', () => {
  let component: ListaVoci;
  let fixture: ComponentFixture<ListaVoci>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListaVoci],
    }).compileComponents();

    fixture = TestBed.createComponent(ListaVoci);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
