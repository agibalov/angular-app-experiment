import {ApiError} from "./api-error";

export class UserAlreadyRegisteredApiError extends ApiError {
    constructor() {
        super('User already exists');
        Object.setPrototypeOf(this, UserAlreadyRegisteredApiError.prototype);
    }
};
