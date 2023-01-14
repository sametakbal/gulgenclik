import axios from 'axios';

export default class BilliardTableService {
  apiUrl = 'api/billiard';
  sessionApiUrl = 'api/sessions';

  public getTables(term: string): Promise<any> {
    return axios.get(this.apiUrl + `/${term}`);
  }

  public openSession(billiardId: number): Promise<any> {
    return axios.post(`${this.sessionApiUrl}/billiard/${billiardId}`);
  }

  public getSession(billiardId: number): Promise<any> {
    return axios.get(`${this.sessionApiUrl}/billiard/${billiardId}`);
  }

}
