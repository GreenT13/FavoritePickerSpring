import { Injectable } from '@angular/core';
import {Http, Response, Headers} from '@angular/http';

@Injectable()
export class HttpClient {
  accessToken: string;
  headers: Headers = new Headers();
  tsGetAccessToken: Date;
  EXPIRATION_TIME = 8400000;

  constructor(private http: Http) { }

  getAccessToken(name: string, password: string) {
    // Send request to the server with the name and password.
    const request = this.http.post('/api/login', {username: name, password: password});
    request.subscribe(
        (response: Response) => {
            this.accessToken = response.headers.get('authorization');
            this.headers.append('Authorization', this.accessToken);
            this.tsGetAccessToken = new Date;
        },
      (error: Error) => {
          // Do nothing?
    }
    );
    return request;
  }

  isLoggedIn(): boolean {
    if (this.accessToken === undefined) {
      return false;
    }
    // Check if token is expired.
    if (this.tsGetAccessToken.getTime() + this.EXPIRATION_TIME < (new Date).getTime()) {
      return false;
    }
    return true;
  }

  get(url: string) {
    return this.http.get(url, { headers: this.headers} );
  }

  post(url: string, body: object) {
    return this.http.post(url, body, {headers: this.headers} );
  }


}
