import { Component } from '@angular/core';
import {HttpClient} from './services/httpclient.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(public httpClient: HttpClient) { }
}
