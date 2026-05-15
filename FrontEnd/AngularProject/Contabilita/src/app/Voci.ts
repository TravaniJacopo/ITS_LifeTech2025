import { Injectable } from "@angular/core";
import { IVoce } from "./models/voci.interface";

@Injectable({
  providedIn: 'root'
})

export class Voci {

    private lista : IVoce[] = [];


    addElemento(elemento: IVoce) {
        this.lista.push(elemento);
    }

    getVoci() {
        return this.lista;
    }


    
}