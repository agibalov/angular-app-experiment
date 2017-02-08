import {UserDto} from "./user-dto";
import {Http} from "@angular/http";
import {Injectable} from "@angular/core";

@Injectable()
export class UserService {
    constructor(private http: Http) {
    }

    async getUser(userId: number): Promise<UserDto> {
        const getUserResponse = await this.http.get(`/api/users/${userId}`).toPromise();
        const userDto = <UserDto>getUserResponse.json();
        return userDto;
    }
}
