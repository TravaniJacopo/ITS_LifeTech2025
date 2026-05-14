import { Component, inject } from '@angular/core';
import {  recensione } from '../recensione-service';
@Component({
  selector: 'app-lista',
  imports: [],
  templateUrl: './lista.html',
  styleUrl: './lista.scss',
})
export class Lista {

  service = inject(recensione);
  lista= this.service.getLista();

}
