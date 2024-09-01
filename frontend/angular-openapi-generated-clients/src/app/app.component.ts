import {Component, inject} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {HttpClient} from "@angular/common/http";
import {AsyncPipe} from "@angular/common";
import {PersonControllerService} from "@generated-clients";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, AsyncPipe],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'angular-http';

  http = inject(HttpClient)

  personControllerService = inject(PersonControllerService)

  $persons = this.getPersons();

  getPersons() {
    return this.personControllerService.getAllPersons();
  }
}
