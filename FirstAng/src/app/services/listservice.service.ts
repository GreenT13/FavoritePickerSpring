import {Injectable} from '@angular/core';
import {Response} from '@angular/http';
import 'rxjs/add/operator/map';
import {List} from '../model/list.model';
import {HttpClient} from './httpclient.service';

@Injectable()
export class ListService {
  constructor(private http: HttpClient) {}
  retrieveLists() {
    // Make get-request to retrieve the lists.
    return this.http.get('/api/lists?logonUserId=1')
      .map(
        (response: Response) => {
          return response.json();
        }
      );
  }

  retrieveList(listId: number) {
    return this.http.get('/api/list?listId=' + listId)
      .map(
        (response: Response) => {
          return response.json();
        }
      );
  }

  retrieveListItem(listId: number) {
    return this.http.get('/api/list-item?listId=' + listId)
      .map(
        (response: Response) => {
          return response.json();
        }
      );
  }

  addNewList(list: List) {
    return this.http.post('/api/add-list?logonUserId=1', list)
      .map(
        (response: Response) => {
          return response.text();
        }
      );
  }
}
