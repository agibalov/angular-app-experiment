import {Component, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";

@Component({
    template: `
<h1>Home</h1>
<p>API says: {{text}}</p>
`
})
export class HomePageComponent implements OnInit {
    text: string;

    constructor(private route: ActivatedRoute) {
    }

    ngOnInit() {
        this.text = (<any>this.route.snapshot.data).hello;
    }
}
