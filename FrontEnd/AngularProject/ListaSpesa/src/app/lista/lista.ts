import { Component, inject } from '@angular/core';
import { ListaSpesa } from '../ListaSpesa';

@Component({
  selector: 'app-lista',
  imports: [],
  templateUrl: './lista.html',
  styleUrl: './lista.scss',
})
export class Lista {
  service = inject(ListaSpesa);
  lista= this.service.getLista();

  removeElemento(id: number) {
    this.service.removeElement(id);
    this.lista = this.service.getLista();
  }
}
