import { Component, inject } from '@angular/core';
import { recensione } from '../recensione-service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-nuovarecensione',
  imports: [FormsModule],
  templateUrl: './nuovarecensione.html',
  styleUrl: './nuovarecensione.scss',
})
export class Nuovarecensione {

  service = inject(recensione);

  descrizione: string = '';
  voto: number = 0;
  nome: string = '';

  addElemento() {
    if(this.descrizione && this.voto != 0)
    {
      this.service.addElemento({ descrizione: this.descrizione, voto: this.voto, nome: this.nome })
      this.descrizione = '';
      this.voto = 0;
      this.nome = '';
    }
  }
}