import { Component, inject } from '@angular/core';
import { Voci } from '../Voci';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-inserisci-voce',
  imports: [FormsModule],
  templateUrl: './inserisci-voce.html',
  styleUrl: './inserisci-voce.scss',
})
export class InserisciVoce {

  service= inject( Voci);

  descrizione: string = '';
  importo: number = 0;
  flag: boolean = false;


  addElemento() {
    if(this.descrizione && this.importo != 0)
    {
      this.service.addElemento({ desc: this.descrizione, importo: this.importo, flag: this.flag })
      this.descrizione = '';
      this.importo = 0;
      this.flag = false;
    }
  }


}
