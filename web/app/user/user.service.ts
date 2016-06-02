import { User } from './user';

import { USERS } from './mock-users';
import { Injectable } from '@angular/core';
import { Observable }     from 'rxjs/Observable';
import { Http, Headers, Response } from '@angular/http';

@Injectable()
export class UserService {
    /*
    getUsers() {
        return Promise.resolve(USERS);
    }*/

    private usersUrl = 'http://localhost:8080/users';  // URL to web API

    constructor (private http: Http) {}

    // See the "Take it slow" appendix
    getUserSlowly() {
        return new Promise<User[]>(resolve =>
            setTimeout(()=>resolve(USERS), 2000) // 2 seconds
        );
    }

    getUser(id: number) {
        return Promise.resolve(USERS).then(
            users => users.filter(user => user.id === id)[0]
        );
    }

    getUsers (): Observable<User[]> {

        console.log("loading users");
        
        return this.http.get(this.usersUrl)
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