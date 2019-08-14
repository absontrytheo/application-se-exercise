import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import { FormsModule }   from '@angular/forms';

import {AppComponent} from './app.component';
import {ScratchComponent} from './scratch/scratch.component';
import {HttpClientModule} from "@angular/common/http";
import {PurchaseComponent} from "./purchase/purchase.component";
import {PurchaseEditorComponent} from "./purchase/purchaseeditor/purchaseeditor.component";


@NgModule({
  declarations: [
    AppComponent,
    PurchaseComponent,
    ScratchComponent,
    PurchaseEditorComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
