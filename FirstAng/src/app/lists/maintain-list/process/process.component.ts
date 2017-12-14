import {Component, OnInit} from '@angular/core';
import {Choice} from '../../../model/choice.model';
import {AlgorithmService} from '../../../services/algorithm.service';
import {ActivatedRoute, Router} from '@angular/router';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-process',
  templateUrl: './process.component.html',
  styleUrls: ['./process.component.css']
})
export class ProcessComponent implements OnInit {
  choice: Choice = new Choice(null);
  listId: number;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private algorithmService: AlgorithmService) { }

  ngOnInit() {
    this.listId = +this.route.snapshot.params['id'];
    this.algorithmService.getChoice(this.listId).subscribe(
      (choice: Choice) => {
        this.choice = choice;
      }
    );
  }

  onSubmit(form: NgForm) {
    // Submit the choice we made.
    this.algorithmService.makeChoice(this.choice, this.listId).subscribe(
      (choice: Choice) => {
        this.choice = choice;

        // If we get a list of 0 items, we finished the process.
        if (this.choice.listItems.length === 0) {
          // Go back to to the list.
          this.router.navigate(['..'], {relativeTo: this.route});
        }
      }
    );
  }

}
