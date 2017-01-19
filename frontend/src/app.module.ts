import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {RouterModule, Routes} from '@angular/router';
import {AppComponent} from "./app.component";
import {NotFoundPageComponent} from "./not-found-page.component";
import {HomePageComponent} from "./home-page.component";
import {HttpModule} from "@angular/http";
import {SignUpPageComponent} from "./sign-up-page.component";
import {AuthenticationService} from "./authentication.service";
import {SignInPageComponent} from "./sign-in-page.component";
import {FormsModule} from "@angular/forms";

const appRoutes: Routes = [
    { path: '', component: HomePageComponent },
    { path: 'sign-up', component: SignUpPageComponent },
    { path: 'sign-in', component: SignInPageComponent },
    { path: '**', component: NotFoundPageComponent }
];

@NgModule({
    imports: [
        BrowserModule,
        HttpModule,
        FormsModule,
        RouterModule.forRoot(appRoutes)
    ],
    declarations: [
        AppComponent,
        HomePageComponent,
        SignUpPageComponent,
        SignInPageComponent,
        NotFoundPageComponent
    ],
    providers: [
        AuthenticationService
    ],
    bootstrap: [ AppComponent ]
})
export class AppModule {
}
