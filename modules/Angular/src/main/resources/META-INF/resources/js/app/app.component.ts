import { Component } from '@angular/core';

@Component({
	template: `
		<div>{{caption}}</div>
	`
})
export class AppComponent {
	caption = 'Hello my world!';
}