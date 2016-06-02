import { Component, OnInit } from '@angular/core';

import {Routes, Router, ROUTER_DIRECTIVES } from '@angular/router';


import { App } from './app';
import { AppService } from './app.service';

@Component({
    selector: 'my-apps',
    directives: [ROUTER_DIRECTIVES],
    providers: [AppService],
    templateUrl: 'app/apps/apps.component.html',
    styleUrls:  ['app/apps/apps.component.css']
})
export class AppsComponent implements OnInit {
    apps: App[];
    selectedApp: App;

    constructor(
        private router: Router,
        private appService: AppService) { }

    getApps() {
        this.appService.getApps().subscribe(apps => this.apps = apps);
    }

    ngOnInit() {
        this.getApps();
    }

    onSelect(app: App) { this.selectedApp = app; }

    gotoDetail() {
        this.router.navigate(['AppDetail', { id: this.selectedApp.id }]);
    }
}


/*
 Copyright 2016 Google Inc. All Rights Reserved.
 Use of this source code is governed by an MIT-style license that
 can be found in the LICENSE file at http://angular.io/license
 */