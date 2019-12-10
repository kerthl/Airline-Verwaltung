import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DetailsComponent } from './details/details.component';
import { KarteComponent } from './karte/karte.component';


const routes: Routes = [
  { path: '', component: KarteComponent },
  { path: 'Karte', component: KarteComponent },
  { path: 'Flughafen', component: DetailsComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }


