import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Purchase} from "./purchase";

@Component({
  selector: 'app-purchase',
  templateUrl: './purchase.component.html',
  styleUrls: ['./purchase.component.css']
})
export class PurchaseComponent implements OnInit {


  purchases: Purchase[];
  loadedAt: string;

  newPurchaseFormIsOpen = false;

  constructor(private httpClient: HttpClient) {
  }

  ngOnInit() {
  }

  deletePurchase(id: number) {
    this.httpClient.delete('api/purchases/' + id)
      .subscribe(resp => {
        // NOTE: also here error handling would be nice
        this.onLoadPurchasesButtonClick();
      });
  }

  onLoadPurchasesButtonClick() {
    this.httpClient.get<Purchase[]>("api/purchases")
    //NOTE: ideally, we should have an error handler here, which we left away for simplicity
      .subscribe(resp => {
        this.purchases = resp;
      });

    this.loadedAt = new Date().toLocaleTimeString();
  }

  removePurchases() {
    this.purchases = null;
  }

  switchNewPurchaseForm() {
    this.newPurchaseFormIsOpen = !this.newPurchaseFormIsOpen;
  }
}
