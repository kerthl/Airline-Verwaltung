import { Component, OnInit } from '@angular/core';
import Swiper from 'swiper';
import { RequestService } from '../services/request.service';
import { IfStmt } from '@angular/compiler';
import { ActivatedRoute, Params } from '@angular/router';


@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./swiper.min.css', './details.component.css']
})
export class DetailsComponent implements OnInit {

  private flughafenAbId = '';
  private bezeichnung = '';
  private ort = '';
  private code = '';

  private flughafen: any = [];

  private angebote: any = [];

  private airlines: any = [];
  protected requestService: RequestService;

  constructor(
    requestService: RequestService,
    public route: ActivatedRoute) {
    this.requestService = requestService;
  }

  ngOnInit() {
    this.route.params.forEach((params: Params) => {
      this.flughafenAbId = params['id'];
  });
    const airlines = this.requestService.fetchAirlines();
    const airports = this.requestService.fetchAirports();
    const offers = this.requestService.fetchOffer(this.flughafenAbId);

    airlines.subscribe((req: any) => {
      this.airlines = req;
    });
    airports.subscribe((req: any) => {
      this.flughafen = req;
      this.setAirport();
    });
    offers.subscribe((req: any) => {
      this.angebote = req;
    });

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

  setAirport() {
    const airport = this.flughafen.find(hafen => hafen.id == this.flughafenAbId);
    this.bezeichnung = airport.bezeichnung;
    this.ort = airport.ort;
    this.code = airport.code;
  }

  getAirline(airlineId) {
    return this.airlines.find(airline => airline.id === airlineId).bezeichnung;
  }

  getAirport(flughafenId) {
    return this.flughafen.find(hafen => hafen.id === flughafenId).bezeichnung;
  }

}
