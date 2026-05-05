import { Component, input, output } from '@angular/core';
import { IArticolo } from '../lista-articoli/articolo.interface';

@Component({
  selector: 'app-articolo',
  imports: [],
  templateUrl: './articolo.html',
  styleUrl: './articolo.scss',
})
export class Articolo {
  articolo = input.required<IArticolo>();
  aggiungi = output<number>();
  rimuovi = output<number>();
  outputaggiungi()
  {
    this.aggiungi.emit(this.articolo().id);
  }
  outputrimuovi()
  {
    this.rimuovi.emit(this.articolo().id);
  }

}
