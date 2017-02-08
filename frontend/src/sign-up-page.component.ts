import {Component} from "@angular/core";
import {Router} from "@angular/router";
import {AuthenticationService} from "./api/authentication.service";
import {UserAlreadyRegisteredApiError} from "./api/user-already-registered-api-error";
import {UnknownApiError} from "./api/unknown-api-error";

@Component({
    template: `
<h1>Sign Up</h1>

<form (ngSubmit)="signUp()">
    <fieldset [disabled]="wip">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" id="username" placeholder="Username" name="username" [(ngModel)]="username">
        </div>
        <button type="submit" class="btn btn-default">Sign Up</button>
    </fieldset>
</form>
`
})
export class SignUpPageComponent {
    wip: boolean;
    username: string = '';

    constructor(
        private router: Router,
        private authenticationService: AuthenticationService) {
    }

    async signUp(): Promise<void> {
        this.wip = true;
        try {
            await this.authenticationService.signUp({
                username: this.username
            });

            this.router.navigate(['/']);
        } catch(e) {
            if(e instanceof UserAlreadyRegisteredApiError) {
                console.log('User already exists', e);
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
