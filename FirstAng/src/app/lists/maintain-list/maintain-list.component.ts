import { List } from '../../model/list.model';
import { ListService } from '../../services/listservice.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {ListItem} from '../../model/listitem.model';

@Component({
  selector: 'app-maintain-list',
  templateUrl: './maintain-list.component.html',
  styleUrls: ['./maintain-list.component.css']
})
export class MaintainListComponent implements OnInit {
  // Add default values because otherwise we cannot display a page.
  listItems: ListItem[] = [];
  list: List = new List(null, null, null, null);

  constructor(private route: ActivatedRoute, private listService: ListService) { }

  ngOnInit() {
    this.listService.retrieveListItem(+this.route.snapshot.params['id']).subscribe(
      (listItems: ListItem[]) => {
        this.listItems = listItems;
      }
    );
    this.listService.retrieveList(+this.route.snapshot.params['id']).subscribe(
      (list: List) => {
        this.list = list;
      }
    )
  }

}
