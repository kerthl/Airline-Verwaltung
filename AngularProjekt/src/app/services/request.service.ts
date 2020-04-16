import { HttpService } from './http.service';
import { Injectable } from '@angular/core';
@Injectable({
    providedIn: 'root',
})
/**
 * RequestService is responsible for managing request/request CRUD operations.
 */
export class RequestService {

    constructor(
        public httpService: HttpService
    ) {
    }
    /**
     * used to get data from API
     */
    public fetchAirlines() {
        return this.httpService.fetch('/AirlineListe');
    }
    /**
     * used to get data from API
     */
    public fetchAirports() {
        return this.httpService.fetch('/FlughafenListe');
    }

    public fetchAngeboteForOneAirport(id) {
        return this.httpService.fetch('/FlughafenDetail/' + id + '/getAngebote');
    }

    /**
     * used to get data from API
     */
    public fetchFlights() {
        return this.httpService.fetch('/FlugListe');
    }
    /**
     * used to post data to API
     * @param data Request-object
     */
    public post(data: any) {
        return this.httpService.post('/requests', data);
    }

    /**
     * Used to update Request
     * @param data New data
     * @param id ID of request Object
     */
    public put(data: any, id: string) {
        return this.httpService.put('/requests/' + id, data);
    }


}
