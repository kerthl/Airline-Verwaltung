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
     * @param id ID of request-object
     */
    public fetchAirports() {
        return this.httpService.fetch('/FlughafenListe');
    }
    /**
     * uset to post data to API
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