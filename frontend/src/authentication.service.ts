import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";

export class UserDto {
    userId: number;
    name: string;
    postCount: number;
    commentCount: number;
    followerCount: number;
    followsCount: number;
}

export abstract class ApiError extends Error {
    constructor(message: string) {
        super(message);
    }
}

export class UserAlreadyExistsApiError extends ApiError {
    constructor() {
        super('User already exists');
        Object.setPrototypeOf(this, UserAlreadyExistsApiError.prototype);
    }
}

export class UnknownApiError extends ApiError {
    constructor() {
        super('Unknown API error');
        Object.setPrototypeOf(this, UnknownApiError.prototype);
    }
}

@Injectable()
export class AuthenticationService {
    constructor(private http: Http) {
    }

    async signUp(credentials: { username: string }): Promise<void> {
        let postUserResponse: Response;
        try {
            postUserResponse = await this.http.post('/api/users', {
                username: credentials.username
            }).toPromise();
        } catch(e) {
            if(e instanceof Response) {
                const response = <Response>e;
                if(response.status == 400) {
                    throw new UserAlreadyExistsApiError();
                } else {
                    throw new UnknownApiError();
                }
            }
        }

        if(postUserResponse != null) {
            const userLocation = postUserResponse.headers.get('location');
            const getUserResponse = await this.http.get(userLocation).toPromise();
            const userDto = <UserDto>getUserResponse.json();
            console.log(userDto);
        }
    }

    async signIn(credentials: { username: string }): Promise<void> {
        // TODO
    }
}
