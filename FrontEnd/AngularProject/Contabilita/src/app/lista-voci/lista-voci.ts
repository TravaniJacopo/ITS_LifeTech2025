import { Component, inject } from '@angular/core';
import { Voci } from '../Voci';

@Component({
  selector: 'app-lista-voci',
  imports: [],
  templateUrl: './lista-voci.html',
  styleUrl: './lista-voci.scss',
})
export class ListaVoci {

    service = inject(Voci);
    lista = this.service.getVoci();

  saldo()
  {
    let totaleEntrate:number = 0;
    let totaleUscite:number = 0;

    this.lista.forEach(el => {
      if(el.flag)
      {
        totaleEntrate += el.importo;
      }
      else
      {
        totaleUscite += el.importo;
      }
    });
    return totaleEntrate - totaleUscite;
  }


}
