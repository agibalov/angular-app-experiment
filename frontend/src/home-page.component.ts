import {Component, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {PostDto} from "./api/post-dto";

@Component({
    template: `
<h1>Home</h1>
<ul *ngIf="posts.length > 0">
    <li *ngFor="let post of posts">#{{post.postId}}: {{post.text}}</li>
</ul>
<p *ngIf="posts.length == 0">There are no posts</p>
`
})
export class HomePageComponent implements OnInit {
    posts: PostDto[];

    constructor(private route: ActivatedRoute) {
    }

    ngOnInit() {
        this.posts = (<any>this.route.snapshot.data).posts;
    }
}
