import { Component, OnInit } from '@angular/core';

import {Routes, Router, ROUTER_DIRECTIVES } from '@angular/router';


import { User } from './user';
import { UserService } from './user.service';

@Component({
    selector: 'my-users',
    directives: [ROUTER_DIRECTIVES],
    providers: [UserService],
    templateUrl: 'app/user/users.component.html',
    styleUrls:  ['app/user/users.component.css']
})
export class UsersComponent implements OnInit {
    users: User[];
    selectedUser: User;

    constructor(
        private router: Router,
        private userService: UserService) { }

    getUsers() {
        this.userService.getUsers().subscribe(users => this.users = users);
    }

    ngOnInit() {
        this.getUsers();
    }

    onSelect(user: User) { this.selectedUser = user; }

    gotoDetail() {
        this.router.navigate(['UserDetail', { id: this.selectedUser.id }]);
    }
}


/*
 Copyright 2016 Google Inc. All Rights Reserved.
 Use of this source code is governed by an MIT-style license that
 can be found in the LICENSE file at http://angular.io/license
 */