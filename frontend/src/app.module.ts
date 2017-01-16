import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {RouterModule, Routes} from '@angular/router';
import {AppComponent} from "./app.component";
import {NotFoundPageComponent} from "./not-found-page.component";
import {HomePageComponent} from "./home-page.component";
import {PageOneComponent} from "./page-one.component";
import {PageTwoComponent} from "./page-two.component";
import {HttpModule} from "@angular/http";

const appRoutes: Routes = [
    { path: '', component: HomePageComponent },
    { path: 'page1', component: PageOneComponent },
    { path: 'page2', component: PageTwoComponent },
    { path: '**', component: NotFoundPageComponent }
];

@NgModule({
    imports: [
        BrowserModule,
        HttpModule,
        RouterModule.forRoot(appRoutes)
    ],
    declarations: [
        AppComponent,
        HomePageComponent,
        PageOneComponent,
        PageTwoComponent,
        NotFoundPageComponent
    ],
    providers: [
    ],
    bootstrap: [ AppComponent ]
})
export class AppModule {
}
