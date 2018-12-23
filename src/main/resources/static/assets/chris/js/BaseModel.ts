declare let layui,layer;
class BaseModel{
    private _url: {
        listByPage: string;
        create: string;
        update: string;
        delete: string;
        view: string;
        edit: string;
        add: string;
    };
    private _params: any = {};
    private _columns: Object;
    private _id: string;
    private _title: string;
    private _entity: any ={};
    private _rowId: any = 'id';
    private _modalHide: boolean = true;
    private _rowName: string = 'name';
    private _modalVue: any;

    init() {
        this.initPost(this);
        let that = this;
        layui.use('table', function(){
            let table = layui.table;
                //第一个实例
                table.render({
                    elem: `#${that.id}_table`,
                    height: 312,
                    url: that.url.listByPage,
                    page: true,
                    cols: that.columns ,
                    toolbar: `#${that.id}_toolbar`,
                    totalRow: true,
                    title: '用户数据表',
                    response: {
                        statusCode: 200 //规定成功的状态码，默认：0
                    },
                    parseData: function(res){ //将原始数据解析成 table 组件所规定的数据
                        console.log(res);
                        res.data = res.data ? (res.data||[]) : []
                        return {
                            "code": 200, //解析接口状态
                            "msg": res.message, //解析提示文本
                            "count": res.total, //解析数据长度
                            "data": res.rows //解析数据列表
                        };
                    }
                });
                //头工具栏事件
                table.on(`toolbar(${that.id}_table)`, function(obj){

                    switch(obj.event){
                        case 'getCheckData':
                            var checkStatus = table.checkStatus(obj.config.id); //获取选中行状态
                            var data = checkStatus.data;  //获取选中行数据
                            layer.alert(JSON.stringify(data));
                            break;
                        case 'add':

                            DM.xAdminShowMadol(that.title,that.url.add,null,null,function (msg) {
                                that.showModalInit();
                            });

                    };
                });
                //监听行工具事件
                table.on(`tool(${that.id}_table)`, function(obj){
                    var data = obj.data;
                    //console.log(obj)
                    if(obj.event === 'del'){
                        layer.confirm('真的删除行么', function(index){
                            DM.post(that.url.delete,{id :obj.data.id},function (msg) {
                                if(msg.success){
                                    layer.alert(msg.message);
                                }else{

                                }
                            },false);
                            // obj.del();
                            layer.close(index);
                        });
                    } else if(obj.event === 'edit'){
                        layer.prompt({
                            formType: 2
                            ,value: data.email
                        }, function(value, index){
                            obj.update({
                                email: value
                            });
                            layer.close(index);
                        });
                    }
                });
        });
    };

    showModalInit(){
        let that = this;
        new Vue({
            el:`#${this.id}_form`,
            data:{
                obj:that.entity
            },
            mounted(){
                console.log("Vue初始化成功！");
            }
        });
    };

    viewInit(that, data, id) {

    }

    initPost(that) {

    }


    get url(): { listByPage: string; create: string; update: string; delete: string; view: string; edit: string; add: string } {
        return this._url;
    }

    set url(value: { listByPage: string; create: string; update: string; delete: string; view: string; edit: string; add: string }) {
        this._url = value;
    }

    get params(): any {
        return this._params;
    }

    set params(value: any) {
        this._params = value;
    }

    get columns(): Object {
        return this._columns;
    }

    set columns(value: Object) {
        this._columns = value;
    }

    get id(): string {
        return this._id;
    }

    set id(value: string) {
        this._id = value;
    }

    get entity(): Object {
        return this._entity;
    }

    set entity(value: Object) {
        this._entity = value;
    }

    get rowId(): any {
        return this._rowId;
    }

    set rowId(value: any) {
        this._rowId = value;
    }

    get modalHide(): boolean {
        return this._modalHide;
    }

    set modalHide(value: boolean) {
        this._modalHide = value;
    }

    get rowName(): string {
        return this._rowName;
    }

    set rowName(value: string) {
        this._rowName = value;
    }

    get modalVue(): any {
        return this._modalVue;
    }

    set modalVue(value: any) {
        this._modalVue = value;
    }

    get title(): string {
        return this._title;
    }

    set title(value: string) {
        this._title = value;
    }
}