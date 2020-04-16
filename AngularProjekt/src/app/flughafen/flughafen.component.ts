import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RequestService } from '../services/request.service';


@Component({
  selector: 'app-flughafen',
  templateUrl: './flughafen.component.html',
  styleUrls: ['./flughafen.component.css']
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
  }
}
