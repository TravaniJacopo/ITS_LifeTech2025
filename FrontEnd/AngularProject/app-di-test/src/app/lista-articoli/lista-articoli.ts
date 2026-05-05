import { Component } from '@angular/core';
import { IArticolo } from './articolo.interface';
import { IElementoCarrello } from './elemento-carrello.interface';
import { Articolo } from "../articolo/articolo";

@Component({
  selector: 'app-lista-articoli',
  imports: [Articolo],
  templateUrl: './lista-articoli.html',
  styleUrl: './lista-articoli.scss',  

})
export class ListaArticoli {

  listaArticoli:IArticolo[]=[
    {id: 1, name: 'Articolo1', price: 20},
    {id: 2, name: 'Articolo2', price: 30},
    {id: 3, name: 'Articolo3', price: 40}, 
    {id: 4, name: 'Articolo4', price: 50}
  ];


  listaArticoliNelCarrello:IElementoCarrello []=[];

  addElementoCarrello(id: number) {
    let el = this.listaArticoliNelCarrello.find((s:any) => s.id === id);
    if (el) {
      el.quantity = el.quantity + 1;
    } else {
       let elToAdd = this.listaArticoli.find((a: IArticolo) => a.id === id);
      if (elToAdd) {
      this.listaArticoliNelCarrello.push({
        ...elToAdd,
        quantity: 1
      });
    }
  }};


  getTotale() {
    let total = 0;
    if (this.listaArticoliNelCarrello.length === 0) {
      return total;
    }
    else {  
    this.listaArticoliNelCarrello.forEach((s:any) => {
      total = total + (s.price * s.quantity);
    });
    return total;
       }}

}