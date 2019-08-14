import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "./user";

@Component({
  selector: 'app-scratch',
  templateUrl: './scratch.component.html',
  styleUrls: ['./scratch.component.css']
})
export class ScratchComponent implements OnInit {


  users: User[];

  loadedAt: string;

  constructor(private httpClient: HttpClient) {
  }

  ngOnInit() {
  }


  onLoadUsersButtonClick() {
    this.httpClient.get<User[]>("api/users")
    //NOTE: ideally, we should have an error handler here, which we left away for simplicity
      .subscribe(resp => {
        this.users = resp;
      });

    this.loadedAt = new Date().toLocaleTimeString();
  }


  removeUsers() {
    this.users = null;
  }
}
