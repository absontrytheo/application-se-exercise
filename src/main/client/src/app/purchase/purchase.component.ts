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
  isPurchaseEditable: Map<number, boolean>;
  sumPerBuyer: Map<string, number>;
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
        this.isPurchaseEditable = new Map();
        this.sumPerBuyer = new Map();

        this.purchases.forEach(purchase => {
          this.isPurchaseEditable[purchase.id] = false;
          if (purchase.buyerName in this.sumPerBuyer) {
            this.sumPerBuyer[purchase.buyerName] = this.sumPerBuyer[purchase.buyerName] + purchase.priceInCents;
          } else {
            this.sumPerBuyer[purchase.buyerName] = purchase.priceInCents;
          }
        });

      });

    this.loadedAt = new Date().toLocaleTimeString();
  }

  removePurchases() {
    this.purchases = null;
  }

  switchNewPurchaseForm() {
    this.newPurchaseFormIsOpen = !this.newPurchaseFormIsOpen;
  }

  getTotalPurchaseValue(): number {
    if (this.purchases) {
      return this.purchases.map(purchase => purchase.priceInCents).reduce((a, b) => a + b, 0)
    } else {
      return 0;
    }
  }

  switchPurchaseEditable(id: number) {
    this.isPurchaseEditable[id] = !this.isPurchaseEditable[id];
  }
}
