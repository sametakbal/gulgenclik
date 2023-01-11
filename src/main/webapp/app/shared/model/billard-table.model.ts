export interface IBilliardTable {
  id?: number;
  name?: string;
  price?: number;
  using?: boolean;
}

export class BilliardTable implements IBilliardTable {

  constructor(public id?: number,
              public name?: string,
              public price?: number,
              public using?: boolean) {
  }
}
