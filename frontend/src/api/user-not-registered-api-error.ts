import {ApiError} from "./api-error";

export class UserNotRegisteredApiError extends ApiError {
    constructor() {
        super('User is not registered exists');
        Object.setPrototypeOf(this, UserNotRegisteredApiError.prototype);
    }
};