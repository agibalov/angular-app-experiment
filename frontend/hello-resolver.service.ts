import {RouterStateSnapshot, ActivatedRouteSnapshot, Resolve} from "@angular/router";
import {Http} from "@angular/http";
import {Injectable} from "@angular/core";

@Injectable()
export class HelloResolver implements Resolve<string> {
    constructor(private http: Http) {
    }

    async resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<string> {
        const response = await this.http.get('/api/hello').toPromise();
        return response.text();
    }
};
