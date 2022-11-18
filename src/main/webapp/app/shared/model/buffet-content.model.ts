export interface IBuffetContent {
  id?: number;
  name?: string;
  price?: string;
}

export class BuffetContent implements IBuffetContent {

  constructor(public id?: number,
              public name?: string,
              public price?: string,) {
  }
}
