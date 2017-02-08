import {RouterStateSnapshot, ActivatedRouteSnapshot, Resolve} from "@angular/router";
import {Injectable} from "@angular/core";
import {PostService} from "./api/post.service";
import {PostDto} from "./api/post-dto";

@Injectable()
export class PostsResolver implements Resolve<PostDto[]> {
    constructor(private postService: PostService) {
    }

    async resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<PostDto[]> {
        try {
            return await this.postService.getPosts();
        } catch(e) {
            // Temporary solution
            return [];
        }
    }
}
