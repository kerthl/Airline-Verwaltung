import { Component, OnInit } from '@angular/core';
import Swiper from 'swiper';
import { ActivatedRoute } from '@angular/router';
import { RequestService } from '../services/request.service';


@Component({
  selector: 'app-flughafen',
  templateUrl: './flughafen.component.html',
  styleUrls: ['./swiper.min.css', './flughafen.component.css']
})
export class FlughafenComponent implements OnInit {

  private airports: any = [];

  protected requestService: RequestService;

  constructor(
    requestService: RequestService,
    public route: ActivatedRoute) {
    this.requestService = requestService;
  }

  ngOnInit() {

    const airports = this.requestService.fetchAirports();
    airports.subscribe((req: any) => {
      this.airports = req;
    });
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
}
