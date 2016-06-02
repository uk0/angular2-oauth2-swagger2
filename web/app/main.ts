import {bootstrap}    from '@angular/platform-browser-dynamic';
import {AppComponent} from './app.component';
import {Router} from '@angular/router';
import {HttpInterceptor} from './http/http.interceptor';
import {Http, XHRBackend, HTTP_PROVIDERS, RequestOptions} from '@angular/http';

import {Authentication} from './login/authentication';

import {provide} from '@angular/core';

import { LocationStrategy, HashLocationStrategy } from '@angular/common/index';

import {ROUTER_DIRECTIVES, ROUTER_PROVIDERS } from '@angular/router';
import {} from "http";

bootstrap(AppComponent, [
    ROUTER_DIRECTIVES,
    ROUTER_PROVIDERS,
    HTTP_PROVIDERS,
    provide(LocationStrategy, {useClass: HashLocationStrategy}),
    provide(Http, {
        useFactory: (xhrBackend: XHRBackend, requestOptions:
            RequestOptions, router: Router) =>
            new HttpInterceptor(xhrBackend, requestOptions, router),
        deps: [XHRBackend, RequestOptions, Router]
    }),
    Authentication
]).catch(err => console.error(err));