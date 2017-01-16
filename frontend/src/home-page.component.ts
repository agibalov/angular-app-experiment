import {Component, OnInit} from "@angular/core";
import {Http} from "@angular/http";

@Component({
    template: `
<h1>Home</h1>
<p>API says: {{text}}</p>
`
})
export class HomePageComponent implements OnInit {
    text: string;

    constructor(private http: Http) {
    }

    async ngOnInit(): Promise<void> {
        const response = await this.http.get('/api/hello').toPromise();
        const responseText = response.text();
        this.text = responseText;
    }
}
