import { Component, Inject, Vue } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import BuffetContentsService from "@/admin/buffet-contents/buffet-contents.service";
import {BuffetContent, IBuffetContent} from "@/shared/model/buffet-content.model";

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class BuffetContentsComponent extends Vue {
  @Inject('buffetContentsService') private buffetContentsService: () => BuffetContentsService;

  public buffetContent : IBuffetContent;
  public contents: Array<IBuffetContent>;
  public showDeletePartial = false;

  public constructor() {
    super();
    this.buffetContent = new BuffetContent();
    this.contents = [];
  }

  public mounted(): void {
    this.getAll();
  }

  createBuffetContent(){
    if (this.contentIsValid()) {
      this.buffetContentsService().create(this.buffetContent).then(_res=>{
        this.getAll();
        this.clearForm();
      });
    }
  }

  contentIsValid():boolean{
    return Boolean(this.buffetContent.name && this.buffetContent.price);
  }

  selectContent(content : BuffetContent){
    this.buffetContent = content;
    this.showDeletePartial = false;
  }

  deleteBuffetContent(){
    if (this.buffetContent.id) {
      this.buffetContentsService().delete(this.buffetContent.id).then(_res=>{
        this.getAll();
        this.clearForm();
      });
    }
  }

  clearForm(){
    this.buffetContent = new BuffetContent();
    this.showDeletePartial = false;
  }

  getAll(){
    this.buffetContentsService().getAll().then(res =>{
      this.contents = res.data;
    });
  }


}
