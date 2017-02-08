import {PostDto} from "./post-dto";
import {Http} from "@angular/http";
import {Injectable} from "@angular/core";

@Injectable()
export class PostService {
    constructor(private http: Http) {
    }

    async getPosts(): Promise<PostDto[]> {
        const getPostsResponse = await this.http.get(`/api/posts/`).toPromise();
        const postDtos = <PostDto[]>getPostsResponse.json();
        return postDtos;
    }
}
