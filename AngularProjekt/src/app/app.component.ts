import { Component, HostListener } from '@angular/core';
import * as mapboxgl from 'mapbox-gl';

// tslint:disable: variable-name
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  @HostListener('window:resize', ['$event'])
  map: mapboxgl.Map;
  style = 'mapbox://styles/mapbox/streets-v11';
  lat = 47.61028;
  lng = 13.85583;

  title = 'Angular Fluege Karte';
  private _opened = false;
  private _autoCollapseHeight: number = window.innerHeight * 0.7;
  private _autoCollapseWidth: number = window.innerWidth * 0.7;

  private _toggleOpened(): void {
    this._opened = !this._opened;
  }

  constructor() { }
  ngOnInit() {
    mapboxgl.accessToken = 'pk.eyJ1IjoibWF1Y2hzIiwiYSI6ImNrM3kzaGMxOTBpM3UzZHFjdXN1ZjRsM3UifQ.ZpBwuft2KhU2B_eBKiIkew';
    this.map = new mapboxgl.Map({
      container: 'map',
      style: this.style,
      zoom: 7,
      center: [this.lng, this.lat]
    });    // Add map controls
    this.map.addControl(new mapboxgl.NavigationControl());
  }

}
