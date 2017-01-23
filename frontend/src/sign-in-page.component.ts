import {Component} from "@angular/core";
import {Router} from "@angular/router";
import {AuthenticationService} from "./authentication.service";
import {UserNotRegisteredApiError} from "./user-not-registered-api-error";
import {UnknownApiError} from "./unknown-api-error";

@Component({
    template: `
<h1>Sign In</h1>

<form (ngSubmit)="signIn()">
    <fieldset [disabled]="wip">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" id="username" placeholder="Username" name="username" [(ngModel)]="username">
        </div>
        <button type="submit" class="btn btn-default">Sign In</button>
    </fieldset>
</form>
`
})
export class SignInPageComponent {
    wip: boolean;
    username: string = '';

    constructor(
        private router: Router,
        private authenticationService: AuthenticationService) {
    }

    async signIn(): Promise<void> {
        this.wip = true;
        try {
            await this.authenticationService.signIn({
                username: this.username
            });

            this.router.navigate(['/']);
        } catch(e) {
            if(e instanceof UserNotRegisteredApiError) {
                console.log('User is not registered', e);
            } else if(e instanceof UnknownApiError) {
                console.log('Unknown API error', e);
            } else {
                console.log('Unknown error', e);
            }
        } finally {
            this.wip = false;
        }
    }
}
