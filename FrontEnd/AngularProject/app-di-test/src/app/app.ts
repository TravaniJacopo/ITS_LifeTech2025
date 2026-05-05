import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ListaArticoli } from "./lista-articoli/lista-articoli";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ListaArticoli],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  articles: any[] = [
    {id: 1, name: 'Articolo1', price: '20'},
  
    {id: 2, name: 'Articolo2', price: '30'},
  
    {id: 3, name: 'Articolo3', price: '40'},]

  shoppingList: any[] = [];
  addQuantity(id: number) {

    let el = this.shoppingList.find((s:any) => s.id === id);
    if (el) {
      el.quantity = el.quantity + 1;
    } else {
      let elToAdd = this.articles.find((a:any) => a.id === id);
      elToAdd.quantity = 1;
      this.shoppingList.push(elToAdd);
    }
  }
  reduceQuantity(id: number) {
    let el = this.shoppingList.find((s:any) => s.id === id);
    if (el.quantity > 0) {
      el.quantity = el.quantity - 1;
    }
  }

  getTotal() {
    let total = 0;  
    this.shoppingList.forEach((s:any) => {
      total = total + (s.price * s.quantity);
    });
    return total;
  }
}
