import { Component, OnInit } from '@angular/core';
import Swiper from 'swiper';
import { RequestService } from '../services/request.service';


@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./swiper.min.css', './details.component.css']
})
export class DetailsComponent implements OnInit {

  private bezeichnung = 'Flughafen Wien';
  private code = 'VIE1000';
  private ort = 'Wien';

  private flughafen: any = [{
    id: 1,
    bezeichnung: 'Flughafen Wien',
    code: 'VIE1000',
    ort: 'Wien'
  },
  {
    id: 2,
    bezeichnung: 'Flughafen München',
    code: 'MUN_DE',
    ort: 'München'
  }
  ];

  private angebote: any = [{
    flugNr: 115,
    idAirline: 2,
    idFlughafenAb: 1,
    idFlughafenAn: 2,
    abflugszeit: '18:30',
    ankunftszeit: '20:00'
  },
  {
    flugNr: 230,
    idAirline: 2,
    idFlughafenAb: 2,
    idFlughafenAn: 1,
    abflugszeit: '15:00',
    ankunftszeit: '16:00'
  },
  {
    flugNr: 364,
    idAirline: 1,
    idFlughafenAb: 1,
    idFlughafenAn: 1,
    abflugszeit: '11:00',
    ankunftszeit: '16:00'
  },
  {
    flugNr: 456,
    idAirline: 2,
    idFlughafenAb: 2,
    idFlughafenAn: 2,
    abflugszeit: '15:00',
    ankunftszeit: '24:00'
  },
  {
    flugNr: 824,
    idAirline: 1,
    idFlughafenAb: 2,
    idFlughafenAn: 1,
    abflugszeit: '06:00',
    ankunftszeit: '16:00'
  }
  ];

  private airlines: any = [{
    id: 1,
    idHeimatFH: 1,
    bezeichnung: 'Austrian Airline'
  },
  {
    id: 2,
    idHeimatFH: 2,
    bezeichnung: 'German Airline'
  }];
  protected requestService: RequestService;

  constructor(requestService: RequestService) {
    this.requestService = requestService;
  }

  ngOnInit() {
    const airlines = this.requestService.fetchAirlines();
    const airports = this.requestService.fetchAirports();
    const angebote = this.requestService.fetchOffer();

    angebote.subscribe((req: any) => {
      console.log(req);
    })
  }

  // tslint:disable-next-line: use-lifecycle-interface
  ngAfterViewInit() {
    const swiper = new Swiper('.swiper-container', {
      effect: 'coverflow',
      grabCursor: true,
      centeredSlides: true,
      slidesPerView: 'auto',
      coverflowEffect: {
        rotate: 50,
        stretch: 0,
        depth: 100,
        modifier: 1,
        slideShadows: true,
      },
      pagination: {
        el: '.swiper-pagination',
      },
    });
  }

  getAirline(airlineId) {
    return this.airlines.find(airline => airline.id === airlineId).bezeichnung;
  }

  getAirport(flughafenId) {
    return this.flughafen.find(hafen => hafen.id === flughafenId).bezeichnung;
  }



}
