import {Component} from "@angular/core";
import {AuthenticationService} from "./authentication.service";
import {Router} from "@angular/router";

@Component({
    selector: 'app',
    template: `
<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Dummy</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li [routerLinkActive]="['active']" [routerLinkActiveOptions]="{exact:true}"><a [routerLink]="['']">Home</a></li>
                <li *ngIf="!authenticationService.isAuthenticated" [routerLinkActive]="['active']" [routerLinkActiveOptions]="{exact:true}"><a [routerLink]="['', 'sign-in']">Sign In</a></li>
                <li *ngIf="!authenticationService.isAuthenticated" [routerLinkActive]="['active']" [routerLinkActiveOptions]="{exact:true}"><a [routerLink]="['', 'sign-up']">Sign Up</a></li>
            </ul>
            <form *ngIf="authenticationService.isAuthenticated" class="navbar-form navbar-right" (ngSubmit)="signOut()">
                <fieldset [disabled]="wip">
                    <div class="form-group">
                        <button type="submit" class="btn btn-default">Sign Out</button>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
</nav>
<div class="container">
    <router-outlet></router-outlet>
</div>
`
})
export class AppComponent {
    constructor(
        private router: Router,
        private authenticationService: AuthenticationService) {
    }

    async signOut(): Promise<void> {
        await this.authenticationService.signOut();
        this.router.navigate(['/']);
    }
}
