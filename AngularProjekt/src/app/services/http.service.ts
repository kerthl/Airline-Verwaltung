import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';

@Injectable({
    providedIn: 'root'
})

/**
 * Base class for all other services. Sets some default values for all child services.
 * Also holds base configuration, depending on environment.
 */

export class HttpService {


    constructor(
        public http: HttpClient
    ) { }
    /**
     * Base fetch method to send a GET request.
     * @param path          Specific endpoint on API
     * @param callback      Callback function which is executed, after request was succesful
     */

    public fetch(path: string) {
        return this.http.get(environment.apiUrl + path);
    }
    /**
     * Base post method to send a POST request.
     * @param path          Specific endpoint on API
     * @param body          Request payload e.g. user credentials
     * @param callback      Callback function which is executed, after request was succesful
     */

    public post(path: string, body: any) {
        return this.http.post(environment.apiUrl + path, body);
    }

    /**
     * Base put method to send a PUT request.
     * @param path          Specific endpoint on API
     * @param body          Request payload e.g. user credentials
     * @param callback      Callback function which is executed, after request was succesful
     */
    public put(path: string, body: any) {
        return this.http.put(environment.apiUrl + path, body);
    }
}
