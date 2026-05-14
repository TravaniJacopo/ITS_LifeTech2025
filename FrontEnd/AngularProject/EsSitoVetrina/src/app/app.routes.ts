import { Routes } from '@angular/router';
import { Home } from './home/home';
import { ChiSono } from './chi-sono/chi-sono';
import { Portfolio } from './portfolio/portfolio';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'chi-sono', component: ChiSono },
  { path: 'portfolio', component: Portfolio },
  { path: '**', redirectTo: '' }
];