import {Component, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {PostDto} from "./api/post-dto";
import {UnknownApiError} from "./api/unknown-api-error";
import {PostService, PostAttributesDto} from "./api/post.service";

@Component({
    template: `
<h1>Home</h1>

<form (ngSubmit)="submitPost()">
    <fieldset [disabled]="wip">
        <div class="form-group">
            <label for="content">Content</label>
            <input type="text" class="form-control" id="content" placeholder="Text here" name="content" [(ngModel)]="content">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </fieldset>
</form>

<ul *ngIf="posts.length > 0">
    <li *ngFor="let post of posts">#{{post.postId}}: {{post.text}}</li>
</ul>
<p *ngIf="posts.length == 0">There are no posts</p>
`
})
export class HomePageComponent implements OnInit {
    wip: boolean;
    content: string;
    posts: PostDto[];

    constructor(
        private route: ActivatedRoute,
        private postService: PostService) {
    }

    ngOnInit() {
        this.posts = (<any>this.route.snapshot.data).posts;
    }

    async submitPost(): Promise<void> {
        const postAttributesDto = new PostAttributesDto();
        postAttributesDto.text = this.content;

        this.wip = true;
        try {
            await this.postService.createPost(postAttributesDto);
        } catch(e) {
            if(e instanceof UnknownApiError) {
                console.log('Unknown API error', e);
            } else {
                console.log('Unknown error', e);
            }
        } finally {
            this.wip = false;
        }
    }
}
