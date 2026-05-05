import { Component } from '@angular/core';
import { IArticolo } from './articolo.interface';
import { IElementoCarrello } from './elemento-carrello.interface';
import { Articolo } from "../articolo/articolo";
import { Carrello } from "../carrello/carrello";

@Component({
  selector: 'app-lista-articoli',
  imports: [Articolo, Carrello],
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

  reduceQuantity(id: number) {
    let el = this.listaArticoliNelCarrello.find((s:any) => s.id === id);
    if (el) {
      if (el.quantity > 1) {
        el.quantity = el.quantity - 1;
      } else {
        this.listaArticoliNelCarrello = this.listaArticoliNelCarrello.filter((s:any) => s.id !== id);
      }
    }
  };
}