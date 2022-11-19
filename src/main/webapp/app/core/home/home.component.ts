import Component from 'vue-class-component';
import { Inject, Vue } from 'vue-property-decorator';
import LoginService from '@/account/login.service';

@Component
export default class Home extends Vue {
  @Inject('loginService')
  private loginService: () => LoginService;

  public isOpen = false;
  public height = window.innerHeight * 0.75;

  public openLogin(): void {
    this.loginService().openLogin((<any>this).$root);
  }

  public openSession(){
    this.isOpen = !this.isOpen;
  }

  public get authenticated(): boolean {
    return this.$store.getters.authenticated;
  }

  public get username(): string {
    return this.$store.getters.account?.login ?? '';
  }
}
