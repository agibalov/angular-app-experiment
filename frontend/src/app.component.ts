import {Component} from "@angular/core";

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
                <li [routerLinkActive]="['active']" [routerLinkActiveOptions]="{exact:true}"><a [routerLink]="['page1']">Page 1</a></li>
                <li [routerLinkActive]="['active']" [routerLinkActiveOptions]="{exact:true}"><a [routerLink]="['page2']">Page 2</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <router-outlet></router-outlet>
</div>
`
})
export class AppComponent {
}
