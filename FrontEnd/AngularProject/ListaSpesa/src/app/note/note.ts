import { Component, inject } from '@angular/core';
import { ListaSpesa } from '../ListaSpesa';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-note',
  imports: [FormsModule],
  templateUrl: './note.html',
  styleUrl: './note.scss',
})
export class Note {

  service=inject(ListaSpesa);
  text: string = '';
  addElemento() {
    this.service.addElemento({
      id: Math.floor(Math.random() * 1000),
      desc: this.text,
      
    });
    this.text = '';

  }
}
