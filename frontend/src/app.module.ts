import {NgModule, ErrorHandler} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {RouterModule, Routes} from '@angular/router';
import {AppComponent} from "./app.component";
import {NotFoundPageComponent} from "./not-found-page.component";
import {UserHomePageComponent} from "./user-home-page.component";
import {HttpModule} from "@angular/http";
import {SignUpPageComponent} from "./sign-up-page.component";
import {SignInPageComponent} from "./sign-in-page.component";
import {FormsModule} from "@angular/forms";
import {ProfilePageComponent} from "./profile.component";
import {OuterAppComponent} from "./outer-app.component";
import {InitializerResolver} from "./initializer-resolver.service";
import {PostsResolver} from "./posts-resolver.service";
import {PostService} from "./api/post.service";
import {UserService} from "./api/user.service";
import {AuthenticationService} from "./api/authentication.service";
import {MyErrorHandler} from "./my-error-handler.service";
import {AnonymousHomePageComponent} from "./anonymous-home-page.component";

const appRoutes: Routes = [
    {
        path: '',
        component: AppComponent,
        resolve: {
            dummy: InitializerResolver
        },
        children: [
            { path: '', component: AnonymousHomePageComponent },
            {
                path: 'home',
                component: UserHomePageComponent,
                resolve: {
                    posts: PostsResolver
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
        AnonymousHomePageComponent,
        UserHomePageComponent,
        SignUpPageComponent,
        SignInPageComponent,
        ProfilePageComponent,
        NotFoundPageComponent
    ],
    providers: [
        AuthenticationService,
        UserService,
        PostService,
        InitializerResolver,
        PostsResolver,
        { provide: ErrorHandler, useClass: MyErrorHandler }
    ],
    bootstrap: [ OuterAppComponent ]
})
export class AppModule {
}
