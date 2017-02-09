import {PostDto} from "./post-dto";
import {Http} from "@angular/http";
import {Injectable} from "@angular/core";

export class PostAttributesDto {
    text: string;
}

@Injectable()
export class PostService {
    constructor(private http: Http) {
    }

    async createPost(postAttributesDto: PostAttributesDto): Promise<void> {
        const createPostResponse = await this.http.post(`/api/posts`, postAttributesDto).toPromise();
    }

    async getPosts(): Promise<PostDto[]> {
        const getPostsResponse = await this.http.get(`/api/posts/`).toPromise();
        const postDtos = <PostDto[]>getPostsResponse.json();
        return postDtos;
    }
}
