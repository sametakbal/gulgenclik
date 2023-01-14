import {IUser} from "@/shared/model/user.model";
import {IBilliardTable} from "@/shared/model/billard-table.model";
import {IBuffetContent} from "@/shared/model/buffet-content.model";

export interface ISessionModel {
  id?: number;
  user?: IUser;
  billiardTable?: IBilliardTable;
  buffetContents?: Array<IBuffetContent>;
}

export class SessionModel implements ISessionModel{
  constructor(public id?: number,
              public user?: IUser,
              public billiardTable?: IBilliardTable,
              public buffetContents?: Array<IBuffetContent>) {
  }
}
