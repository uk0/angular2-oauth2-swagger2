import { App } from './app';

import { APPS } from './mock-apps';
import { Injectable } from '@angular/core';
import { Observable }     from 'rxjs/Observable';
import { Http, Headers, Response } from '@angular/http';

@Injectable()
export class AppService {
    /*
    getUsers() {
        return Promise.resolve(USERS);
    }*/

    private appsUrl = 'http://localhost:8080/apps';  // URL to web API

    constructor (private http: Http) {}

    // See the "Take it slow" appendix
    getAppSlowly() {
        return new Promise<App[]>(resolve =>
            setTimeout(()=>resolve(APPS), 2000) // 2 seconds
        );
    }

    getApp(id: number) {
        return Promise.resolve(APPS).then(
            apps => apps.filter(app => app.id === id)[0]
        );
    }

    getApps (): Observable<App[]> {

        console.log("loading apps");
        
        return this.http.get(this.appsUrl)
            .map(this.extractData)
            .catch(this.handleError);
    }
    private extractData(res: Response) {
        console.log('res ' + res.toString());
        let body = res.json();
        console.log('body ' + body);
        return body || { };
    }
    private handleError (error: any) {
        // In a real world app, we might use a remote logging infrastructure
        // We'd also dig deeper into the error to get a better message
        let errMsg = (error.message) ? error.message :
            error.status ? `${error.status} - ${error.statusText}` : 'Server error';
        console.error(errMsg); // log to console instead
        return Observable.throw(errMsg);
    }
}

/*
 Copyright 2016 Google Inc. All Rights Reserved.
 Use of this source code is governed by an MIT-style license that
 can be found in the LICENSE file at http://angular.io/license
 */