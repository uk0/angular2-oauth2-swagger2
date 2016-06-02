import {Component, OnInit} from '@angular/core';

import {Routes, Router, ROUTER_DIRECTIVES } from '@angular/router';

import {Login} from "./login/login";
import {Home} from './home/home';
import {UsersComponent} from "./user/users.component";
import {EnvTabs} from "./envTabs/envTabs";

@Component({
    selector: 'my-app',
    directives: [ROUTER_DIRECTIVES],
    template: `
    <h1>This is the header for all pages, in main app.component.ts html</h1>

    <router-outlet></router-outlet>
  `
})
@Routes([
    { path: '/home', component: Home },
    { path: '/login', component: Login },
    { path: '/users', component: UsersComponent },
    { path: '/tabs', component: EnvTabs }

])
export class AppComponent implements OnInit {
    constructor(private router:Router) {
    }

    ngOnInit() {
        this.router.navigate(['/login']);
    }
}