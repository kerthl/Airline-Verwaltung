import { Component, OnInit } from '@angular/core';
import { RequestService } from 'src/app/services/request.service';
import * as mapboxgl from 'mapbox-gl';

@Component({
  selector: 'app-karte',
  templateUrl: './karte.component.html',
  styleUrls: ['./karte.component.css']
})
export class KarteComponent implements OnInit {
  map: mapboxgl.Map;
  style = 'mapbox://styles/mapbox/streets-v11';
  lat = 47.61028;
  lng = 13.85583;
  protected requestService: RequestService;

  constructor(requestService: RequestService) {
    this.requestService = requestService;
  }

  ngOnInit() {
    mapboxgl.accessToken = 'pk.eyJ1IjoibWF1Y2hzIiwiYSI6ImNrM3kzaGMxOTBpM3UzZHFjdXN1ZjRsM3UifQ.ZpBwuft2KhU2B_eBKiIkew';
    this.map = new mapboxgl.Map({
      container: 'map',
      style: this.style,
      zoom: 7,
      center: [this.lng, this.lat]
    });    // Add map controls
    this.map.addControl(new mapboxgl.NavigationControl());

    const test = this.requestService.fetchAirports();

    test.subscribe((req: any) => {
      for (const fh of req) {
        const popup = new mapboxgl.Popup({ offset: 25 }).setText(
          fh.code + ' ' + fh.bezeichnung
        );
        const marker = new mapboxgl.Marker()
          .setLngLat([fh.laengengrad, fh.breitengrad])
          .setPopup(popup)
          .addTo(this.map);
      }
    });
  }

}

