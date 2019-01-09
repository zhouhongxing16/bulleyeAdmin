declare let layui,layer,form;
class BaseModel{
    private _url: {
        listByPage: string;
        create: string;
        update: string;
        delete: string;
        view: string;
        edit: string;
        add: string;
        getById: string;
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
        let that = this;
        layui.use('table', function(){
            let table = layui.table;
                //第一个实例
                table.render({
                    elem: `#${that.id}_table`,
                    url: that.url.listByPage,
                    page: true,
                    cols: that.columns ,
                    toolbar: `#${that.id}_toolbar`,
                    totalRow: true,
                    title: '用户数据表',
                    id: `${that.id}_reload`,
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
                            that.xAdminShowModal(that.title,that.url.add,null,null,function (msg) {
                                that.showModalInit(null);
                            });

                    };
                });
                //监听行工具事件
                table.on(`tool(${that.id}_table)`, function(obj){
                    var data = obj.data;
                    //console.log(obj)
                    if(obj.event === 'del'){
                        layer.confirm('真的删除行么', function(index){
                            DM.post(that.url.delete+"/"+obj.data.id,null,function (msg) {
                                layer.alert(msg.message);
                            });
                            layer.close(index);
                        });
                    } else if(obj.event === 'edit'){
                        that.xAdminShowModal(that.title,that.url.add,null,null,function (msg) {
                            that.showModalInit(obj.data);
                        });
                    }
                });

        });
    };
    reloadTable(){
        let that = this;
        layui.use('table', function(){
            let table = layui.table;
            let demoReload = $('#demoReload');
            //执行重载
            table.reload(`${that.id}_reload`, {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: that.params
            });
        }  )
    };
    showModalInit(obj){
        let that = this;
        //const dom = $('#staff-add').parent();
        //dom.find('.layui-layer-btn0').attr('v-on:click', 'saveData');
        that.modalVue =  new Vue({
            el:`#${this.id}_form`,
            data:{
                obj:that.entity
            },
            mounted(){
                const thatV = this;
                if(obj!=null){
                    DM.get(that.url.getById+"/"+obj.id,null,function (msg) {
                        if(msg.success&&msg.data!=null){
                            thatV.obj = msg.data;
                        }
                    });
                }
            },
            methods:{
                submitData:function () {
                    let thatV = this;
                    layui.use('form', function(){
                        var form = layui.form;
                        //各种基于事件的操作，下面会有进一步介绍
                        form.on('submit(submitData)', function(data){
                            console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                            console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                            console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
                            thatV.saveData();
                            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
                        });
                    });
                },
                saveData(){
                    let thatV = this;
                    let url = that.url.create;
                    if(obj!=null){
                        url = that.url.update;
                    }
                    DM.post(url,thatV.obj,function (msg) {
                        layer.alert(msg.message);
                        that.reloadTable();
                    });

                }
            }
        });
    };
    xAdminShowModal(title,url,w,h,func) {
        let that = this;
        if (title == null || title == '') {
            title = false;
        };
        if (url == null || url == '') {
            url = "404.html";
        };
        if (w == null || w == '') {
            w = ($(window).width() * 0.5);
        };
        if (h == null || h == '') {
            h = ($(window).height() * 0.5);
        };
        DM.get(url, null, function (msg) {
            //页面层-自定义
            layer.open({
                id: 'staff-add',
                type: 1,
                area: [w + 'px', h + 'px'],
                fix: false, //不固定
                maxmin: true,
                shadeClose: true,
                shade: 0.4,
                title: title,
                content: msg,
                btn: ['保存', '取消'],
                yes: function (index, layero) {
                    layer.close(index)
                    that.modalVue.saveData();
                },
                btn2: function (index, layero) {
                    //按钮【按钮二】的回调

                    //return false 开启该代码可禁止点击该按钮关闭
                },
                success: function (index, layero) {
                    console.log(layero);
                    if (typeof func == 'function') {
                        func();
                    }
                }
            });
        })
    }
    get url(): { listByPage: string; create: string; update: string; delete: string; view: string; edit: string; add: string; getById: string } {
        return this._url;
    }

    set url(value: { listByPage: string; create: string; update: string; delete: string; view: string; edit: string; add: string; getById: string }) {
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