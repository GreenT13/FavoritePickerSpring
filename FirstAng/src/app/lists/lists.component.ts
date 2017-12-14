import { List } from '../model/list.model';
import { ListService } from '../services/listservice.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-lists',
  templateUrl: './lists.component.html',
  styleUrls: ['./lists.component.css']
})
export class ListsComponent implements OnInit {
  lists: List[];

  constructor(private listService: ListService) { }

  ngOnInit() {
    this.listService.retrieveLists().subscribe(
      (lists: List[]) =>  {
        this.lists = lists;
      }
    );
  }
}
