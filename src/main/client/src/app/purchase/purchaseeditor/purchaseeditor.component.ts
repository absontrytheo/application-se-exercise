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
  created: boolean;
  isCreate: boolean;

  ngOnInit() {
    if (!this.purchase) {
      this.purchase = new Purchase();
      this.isCreate = true;
    } else {
      this.isCreate = false;
    }
  }

  createOrUpdatePurchase() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      })
    };
    if (this.isCreate) {
      this.httpClient.post<Purchase>("api/purchases", this.purchase, httpOptions)
      //NOTE: ideally, we should have an error handler here, which we left away for simplicity
        .subscribe(resp => {
          this.purchase = resp;
          this.created = true;

        });
    } else {
      // FIXME: These two are set to undefined because of the same issue I had with the buyDate, namely that I got stuck on getting the conversion working
      this.purchase.creationTime = undefined;
      this.purchase.modificationTime = undefined;
      this.httpClient.put<Purchase>("api/purchases/" + this.purchase.id, this.purchase, httpOptions)
      //NOTE: ideally, we should have an error handler here, which we left away for simplicity
        .subscribe(resp => {
          this.purchase = resp;
          this.created = true;
        });
    }

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
