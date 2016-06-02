import {bootstrap} from '@angular/platform-browser-dynamic';
import {provide} from '@angular/core';
import {HTTP_PROVIDERS, Http, Request, RequestOptionsArgs, Response, XHRBackend, RequestOptions, ConnectionBackend, Headers} from '@angular/http';
import {ROUTER_PROVIDERS, Router} from '@angular/router';
import {LocationStrategy, HashLocationStrategy} from '@angular/common';
import { Observable } from 'rxjs/Observable';
//import * as _ from 'lodash'

//import {MyApp} from './app/my-app';

export class HttpInterceptor extends Http {

    constructor(backend: ConnectionBackend, defaultOptions: RequestOptions, private _router: Router) {
        super(backend, defaultOptions);
    }

    request(url: string | Request, options?: RequestOptionsArgs): Observable<Response> {
        return this.intercept(super.request(url, options));
    }

    get(url: string, options?: RequestOptionsArgs): Observable<Response> {
        console.log('inside interceptor get');
        return this.intercept(super.get(url,this.getRequestOptionArgs(options)));
    }

    post(url: string, body: string, options?: RequestOptionsArgs): Observable<Response> {
        return this.intercept(super.post(url, body, this.getRequestOptionArgs(options)));
    }

    put(url: string, body: string, options?: RequestOptionsArgs): Observable<Response> {
        return this.intercept(super.put(url, body, this.getRequestOptionArgs(options)));
    }

    delete(url: string, options?: RequestOptionsArgs): Observable<Response> {
        return this.intercept(super.delete(url, options));
    }

    getRequestOptionArgs(options?: RequestOptionsArgs) : RequestOptionsArgs {
        console.log('getting request option args');
        if (options == null) {
            options = new RequestOptions();
        }
        if (options.headers == null) {
            options.headers = new Headers();
        } else {
            console.log("headers are " + options.headers.toJSON());
        }

        if (options.headers.get('Content-Type') === null) {
            console.log("Adding header Content-Type");
            options.headers.append('Content-Type', 'application/json');
        }

        let token = localStorage.getItem('token');
        console.log("loaded token " + token);

        if (typeof(token) === 'undefined' ) {

        } else {
            console.log("found token so adding Authorization header");
            options.headers.append("Authorization", "Bearer " + token);
        }


        return options;
    }

    intercept(observable: Observable<Response>): Observable<Response> {
        console.log("inside intercept");
        return observable.catch((err, source) => {
            if (err.status  == 401 /*&& !_.endsWith(err.url, 'api/auth/login')*/) {
                this._router.navigate(['/login']);
                return Observable.empty();
            } else {
                return Observable.throw(err);
            }
        });

    }
}