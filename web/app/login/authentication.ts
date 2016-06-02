// authentication.ts
import {Injectable} from '@angular/core';

import {Observable} from 'rxjs/Rx';



import {Http, Headers, URLSearchParams, HTTP_PROVIDERS} from '@angular/http';

@Injectable()
export class Authentication {
    token:string;

    constructor(public http:Http) {
        this.token = localStorage.getItem('token');
    }

    login(username:string, password:string) {


        var creds = "username=" + username + "&password=" + password + "&grant_type=password&scope=write";
        
        var headers = new Headers();
        var authorization = "Basic " + btoa('foo:bar');
        headers.append('Authorization', authorization);
        headers.append('Content-Type', 'application/x-www-form-urlencoded');

        return this.http.post(
                'http://localhost:8080/oauth/token',
                creds,
                {
                    headers: headers
                }
        )
            .map((res : any) => {
                let data = res.json();

               // console.log("data is " + JSON.parse(data));

                // {"access_token":"ab90cb2d-9ebc-41a9-8061-4243e606701e","token_type":"bearer","refresh_token":"9648523e-3fa4-4a31-827e-7724d914f1f2","expires_in":33048,"scope":"write"}
                console.log("data is " + data);
                this.token = data.access_token;
                console.log("got token from server it is " + this.token);
                localStorage.setItem('token', this.token);
            });
    }

    logout() {
        /*
         * If we had a login api, we would have done something like this

         return this.http.get(this.config.serverUrl + '/auth/logout', {
         headers: new Headers({
         'x-security-token': this.token
         })
         })
         .map((res : any) => {
         this.token = undefined;
         localStorage.removeItem('token');
         });
         */

        this.token = undefined;
        localStorage.removeItem('token');

        return Observable.of(true);
    }

}