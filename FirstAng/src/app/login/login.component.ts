import {Component, OnInit} from '@angular/core';
import {HttpClient} from '../services/httpclient.service';
import {Router} from '@angular/router';
import {Response} from '@angular/http';
import {HttpErrorResponse} from '@angular/common/http';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  name: string;
  password: string;
  errorMessage: string;

  constructor(private httpClient: HttpClient, private router: Router) { }

  ngOnInit() {
  }

  onSubmit() {
    this.httpClient.getAccessToken(this.name, this.password).subscribe(
      (response: Response) => {
          this.router.navigate(['/user']);
    },
      (error: Response) => {
        console.log(error.json());
        console.log(error.json().get('status'));
       // this.errorMessage = error.json().get('message');
    }
    );
  }

}
