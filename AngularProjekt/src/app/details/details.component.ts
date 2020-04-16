import { Component, OnInit } from '@angular/core';
import Swiper from 'swiper';
import { ActivatedRoute, Params } from '@angular/router';
import { RequestService } from '../services/request.service';


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

  private fluege: any = [];

  private airlines: any = [];

  protected requestService: RequestService;

  constructor(
    requestService: RequestService,
    public route: ActivatedRoute) {
    this.requestService = requestService;
  }

  ngOnInit() {
    window.resizeTo(window.innerHeight, window.innerWidth);
    this.route.params.forEach((params: Params) => {
      this.flughafenAbId = params['id'];
    });
    const airlines = this.requestService.fetchAirlines();
    const airports = this.requestService.fetchAirports();
    const flights = this.requestService.fetchFlights();

    airlines.subscribe((req: any) => {
      this.airlines = req;
    });
    airports.subscribe((req: any) => {
      this.flughafen = req;
      this.setAirport();
    });
    flights.subscribe((req: any) => {
      req.forEach(el => {
          if (el.angebot.flughafenAb.id == this.flughafenAbId) {
            this.fluege.push(el);
          }
      });
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
}
