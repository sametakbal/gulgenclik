import Component from 'vue-class-component';
import { Inject, Vue } from 'vue-property-decorator';
import LoginService from '@/account/login.service';
import BilliardTableService from "@/core/home/billiard-table.service";
import { IBilliardTable } from "@/shared/model/billard-table.model";
import { ISessionModel, SessionModel } from "@/shared/model/session.model";
import BuffetContentsService from "@/admin/buffet-contents/buffet-contents.service";
import { IBuffetContent } from "@/shared/model/buffet-content.model";

@Component
export default class Home extends Vue {
  @Inject('loginService')
  private loginService: () => LoginService;

  @Inject('billiardTableService')
  private billiardTableService: () => BilliardTableService;

  @Inject('buffetContentsService')
  private buffetContentsService: () => BuffetContentsService;

  public americans: Array<IBilliardTable>;
  public threeBalls: Array<IBilliardTable>;
  public buffetContents: Array<IBuffetContent>;
  public height = window.innerHeight * 0.75;

  public selectedSession: ISessionModel;

  constructor() {
    super();
    this.americans = [];
    this.threeBalls = [];
    this.buffetContents = [];
    this.selectedSession = new SessionModel();
  }

  public mounted(): void {
    this.loadTables();
    setInterval(this.loadTables, 10000);
    this.loadBuffetContents();
  }

  public openLogin(): void {
    this.loginService().openLogin((<any>this).$root);
  }

  public openSession(id: number) {
    this.billiardTableService().openSession(id).then(_res => {
      this.loadTables();
      this.selectedSession = _res.data;
    });
  }

  public closeSession(id: number) {
    this.billiardTableService().closeSession(id).then(_res => {
      this.loadTables();
      this.selectedSession = _res.data;
    });
  }

  public addBuffetContent(buffetContent: IBuffetContent) {
    this.billiardTableService().addBuffetContent(this.selectedSession.id, buffetContent).then(_res => {
      this.selectedSession = _res.data;
    });
  }

  public getSessionByBilliard(billiardId: number) {
    this.billiardTableService().getSession(billiardId).then(_res => {
      this.selectedSession = _res.data;
      console.log('selected', this.selectedSession);
    });
  }

  public get authenticated(): boolean {
    return this.$store.getters.authenticated;
  }

  public get username(): string {
    return this.$store.getters.account?.login ?? '';
  }

  private loadTables() {
    this.billiardTableService().getTables('american').then(res => {
      this.americans = res.data;
    });
    this.billiardTableService().getTables('threeball').then(res => {
      this.threeBalls = res.data;
    });
  }

  private loadBuffetContents() {
    this.buffetContentsService().getAll().then(res => this.buffetContents = res.data);
  }
}
