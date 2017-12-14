import { Component, OnInit } from '@angular/core';
import {ListService} from '../../services/listservice.service';
import {List} from '../../model/list.model';

@Component({
  selector: 'app-add-list',
  templateUrl: './add-list.component.html',
  styleUrls: ['./add-list.component.css']
})
export class AddListComponent implements OnInit {
  public list: List = new List(null, null, null, 2);
  message: String;

  constructor(private listService: ListService) { }

  ngOnInit() {
  }

  onSubmit() {
    if (this.message === undefined) {
      // Prevent multiple request by condition on message
      this.listService.addNewList(this.list).subscribe(
        (response: String) => {this.message = response; }
      );
    }
  }

}
