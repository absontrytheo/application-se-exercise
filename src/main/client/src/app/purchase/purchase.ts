import DateTimeFormat = Intl.DateTimeFormat;

export class Purchase {
  public id?: number;
  public buyerName: string;
  public productName: string;
  public buyDate: Date;
  public priceInCents: number;
  public creationTime?: string;
  public modificationTime?: string;

}
