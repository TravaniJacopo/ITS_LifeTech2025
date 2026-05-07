import { Component, input } from '@angular/core';

@Component({
  selector: 'app-carrello',
  imports: [],
  templateUrl: './carrello.html',
  styleUrl: './carrello.scss',
})
export class Carrello {

  inputListaArticoliNelCarrello = input.required<any[]>();

    getTotale() {
    let total = 0;
    if (this.inputListaArticoliNelCarrello().length === 0) {
      return total;
    }
    else {  
    this.inputListaArticoliNelCarrello().forEach((s:any) => {
      total = total + (s.price * s.quantity);
    });
    return total;
       }}

       
}
