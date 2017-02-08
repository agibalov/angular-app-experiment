import {RouterStateSnapshot, ActivatedRouteSnapshot, Resolve} from "@angular/router";
import {AuthenticationService} from "./api/authentication.service";
import {Injectable} from "@angular/core";

@Injectable()
export class InitializerResolver implements Resolve<string> {
    constructor(private authenticationService: AuthenticationService) {
    }

    async resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<string> {
        await this.authenticationService.init();
        return 'hello';
    }
}

