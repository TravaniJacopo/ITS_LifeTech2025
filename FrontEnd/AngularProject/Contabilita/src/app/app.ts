import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { InserisciVoce } from './inserisci-voce/inserisci-voce';
import { ListaVoci } from './lista-voci/lista-voci';


@Component({
  selector: 'app-root',
  imports: [RouterOutlet, InserisciVoce, ListaVoci],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('Contabilita');
}
