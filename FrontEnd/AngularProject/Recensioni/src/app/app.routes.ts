import { Routes } from '@angular/router';
import { Nuovarecensione } from './nuovarecensione/nuovarecensione';
import { Lista } from './lista/lista';

export const routes: Routes = [

    {path:"",component:Nuovarecensione},
    {path:"lista",component:Lista},
    {path: '**', redirectTo: ''}
];
