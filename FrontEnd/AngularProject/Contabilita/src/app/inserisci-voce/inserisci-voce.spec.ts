import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InserisciVoce } from './inserisci-voce';

describe('InserisciVoce', () => {
  let component: InserisciVoce;
  let fixture: ComponentFixture<InserisciVoce>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InserisciVoce],
    }).compileComponents();

    fixture = TestBed.createComponent(InserisciVoce);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
