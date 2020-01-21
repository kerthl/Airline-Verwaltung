import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ViewChild } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClient, HttpClientModule } from '@angular/common/http';


import { SidebarModule } from 'ng-sidebar';
import { DetailsComponent } from './details/details.component';
import { KarteComponent } from './karte/karte.component';
import { FlughafenComponent } from './flughafen/flughafen.component';


@NgModule({
  declarations: [
    AppComponent,
    DetailsComponent,
    KarteComponent,
    FlughafenComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    SidebarModule.forRoot(),
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})

 export class AppModule {

}
