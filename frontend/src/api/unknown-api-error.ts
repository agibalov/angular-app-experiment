import {ApiError} from "./api-error";

export class UnknownApiError extends ApiError {
    constructor() {
        super('Unknown API error');
        Object.setPrototypeOf(this, UnknownApiError.prototype);
    }
};