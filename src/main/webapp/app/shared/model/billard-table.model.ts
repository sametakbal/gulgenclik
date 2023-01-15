export interface IBilliardTable {
  id?: number;
  name?: string;
  price?: number;
  using?: boolean;
  totalSessionCost?: number;
  hour?: number;
  minute?: number;
}

export class BilliardTable implements IBilliardTable {

  constructor(public id?: number,
    public name?: string,
    public price?: number,
    public using?: boolean,
    public totalSessionCost?: number,
    public hour?: number,
    public minute?: number) {
  }
}
