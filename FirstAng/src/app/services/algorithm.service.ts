import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';
import 'rxjs/add/operator/map';
import {Choice} from '../model/choice.model';

@Injectable()
export class AlgorithmService {
  constructor(private http: Http) {}

  getChoice(listId: number) {
    // Make get-request to retrieve the lists.
    return this.http.get('/api/algorithm?listId=' + listId)
      .map(
        (response: Response) => {
          return response.json();
        }
      );
  }

  makeChoice(choice: Choice, listId: number) {
    return this.http.post('/api/algorithm?listId=' + listId, choice)
      .map(
        (response: Response) => {
          return response.json();
        }
      );
  }
}
