// home.ts
import {Component} from '@angular/core';
import { CanActivate } from '@angular/router-deprecated';
import {Authentication} from '../login/authentication';
import {isLoggedin}  from '../login/is-loggedin';
import {Routes, Router, ROUTER_DIRECTIVES } from '@angular/router';
import {UsersComponent} from '../user/users.component.ts';


@Component({
    selector: 'home',
    directives: [ROUTER_DIRECTIVES],
    templateUrl: 'app/home/home.html'
})

@CanActivate(() => isLoggedin())
export class Home {
    constructor(public auth: Authentication, public router: Router) {}

    onLogout() {
        this.auth.logout()
            .subscribe(
                () => this.router.navigate(['/login'])
            );
    }
}