import { Inject, Injectable } from "@angular/core";
import { IRecensione } from "./models/recensione.interface";

@Injectable({
  providedIn: 'root'
})

export class recensione {

    private lista : IRecensione[] = [];


    addElemento(elemento: IRecensione) {
        this.lista.push(elemento);
    }

    getLista() {
        return this.lista;
    }

    
}