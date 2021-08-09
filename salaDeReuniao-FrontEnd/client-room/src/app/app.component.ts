import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Room manager';
  subtitle = 'List the rooms created, crate a new room, update or delete them whenever you want!';
}
