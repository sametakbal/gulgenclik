import axios from 'axios';
import {IBuffetContent} from "@/shared/model/buffet-content.model";

export default class BuffetContentsService {
  apiUrl ='api/buffetcontent';

  public create(content: IBuffetContent): Promise<any> {
    return axios.post(this.apiUrl , content);
  }

  public getAll(): Promise<any> {
    return axios.get(this.apiUrl);
  }

}
