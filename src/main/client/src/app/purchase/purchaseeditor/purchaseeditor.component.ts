import {Component, Input, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Purchase} from "../purchase";

@Component({
  selector: 'app-purchase-editor',
  templateUrl: './purchaseeditor.component.html',
  styleUrls: ['./purchaseeditor.component.css']
})
export class PurchaseEditorComponent implements OnInit {

  @Input() purchase: Purchase;


  constructor(private httpClient: HttpClient) {
  }

  loadedAt: string;

  ngOnInit() {
    if (!this.purchase) {
      this.purchase = new Purchase();
    }
  }

  createOrUpdatePurchase() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      })
    };
    this.httpClient.post<Purchase>("api/purchases", this.purchase, httpOptions)
    //NOTE: ideally, we should have an error handler here, which we left away for simplicity
      .subscribe(resp => {
        this.purchase = resp;
      });

    this.loadedAt = new Date().toLocaleTimeString();
  }

  submitted = false;

  onSubmit() {
    this.submitted = true;
  }

  // TODO: Remove this when we're done
  get diagnostic() {
    return JSON.stringify(this.purchase);
  }
}
