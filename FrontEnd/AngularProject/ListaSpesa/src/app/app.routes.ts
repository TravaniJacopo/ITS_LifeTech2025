import { Routes } from '@angular/router';
import { Note } from './note/note';
import { Lista } from './lista/lista';

export const routes: Routes = [
    {path: '', component: Note},
    {path:"lista", component: Lista},
    {path: '**', redirectTo: ''}
];
