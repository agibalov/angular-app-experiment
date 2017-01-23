import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {RouterModule, Routes} from '@angular/router';
import {AppComponent} from "./app.component";
import {NotFoundPageComponent} from "./not-found-page.component";
import {HomePageComponent} from "./home-page.component";
import {HttpModule} from "@angular/http";
import {SignUpPageComponent} from "./sign-up-page.component";
import {AuthenticationService, UserService} from "./authentication.service";
import {SignInPageComponent} from "./sign-in-page.component";
import {FormsModule} from "@angular/forms";
import {ProfilePageComponent} from "./profile.component";
import {OuterAppComponent} from "./outer-app.component";
import {InitializerResolver} from "./initializer-resolver.service";
import {HelloResolver} from "../hello-resolver.service";

const appRoutes: Routes = [
    {
        path: '',
        component: AppComponent,
        resolve: {
            dummy: InitializerResolver
        },
        children: [
            {
                path: '',
                component: HomePageComponent,
                resolve: {
                    hello: HelloResolver
                }
            },
            { path: 'sign-up', component: SignUpPageComponent },
            { path: 'sign-in', component: SignInPageComponent },
            {
                path: 'profile',
                component: ProfilePageComponent
            },
            { path: '**', component: NotFoundPageComponent }
        ]
    }
];

@NgModule({
    imports: [
        BrowserModule,
        HttpModule,
        FormsModule,
        RouterModule.forRoot(appRoutes)
    ],
    declarations: [
        OuterAppComponent,
        AppComponent,
        HomePageComponent,
        SignUpPageComponent,
        SignInPageComponent,
        ProfilePageComponent,
        NotFoundPageComponent
    ],
    providers: [
        AuthenticationService,
        UserService,
        InitializerResolver,
        HelloResolver
    ],
    bootstrap: [ OuterAppComponent ]
})
export class AppModule {
}
