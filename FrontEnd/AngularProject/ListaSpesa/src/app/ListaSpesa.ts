import { Inject, Injectable } from "@angular/core";
import { IListaSpesa } from "./models/listaSpesa.interface";


@Injectable({
  providedIn: 'root'
})

export class ListaSpesa {

    private lista : IListaSpesa[] = [];


    addElemento(elemento: IListaSpesa) {
        this.lista.push(elemento);
    }

    getLista() {
        return this.lista;
    }

    removeElement(id: number) {
        this.lista = this.lista.filter(el => el.id !== id);
    }
    
}