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

}