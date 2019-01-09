var BaseModel = /** @class */ (function () {
    function BaseModel() {
        this._params = {};
        this._entity = {};
        this._rowId = 'id';
        this._modalHide = true;
        this._rowName = 'name';
    }
    BaseModel.prototype.init = function () {
        var that = this;
        layui.use('table', function () {
            var table = layui.table;
            //第一个实例
            table.render({
                elem: "#" + that.id + "_table",
                url: that.url.listByPage,
                page: true,
                cols: that.columns,
                toolbar: "#" + that.id + "_toolbar",
                totalRow: true,
                title: '用户数据表',
                id: that.id + "_reload",
                response: {
                    statusCode: 200 //规定成功的状态码，默认：0
                },
                parseData: function (res) {
                    console.log(res);
                    res.data = res.data ? (res.data || []) : [];
                    return {
                        "code": 200,
                        "msg": res.message,
                        "count": res.total,
                        "data": res.rows //解析数据列表
                    };
                }
            });
            //头工具栏事件
            table.on("toolbar(" + that.id + "_table)", function (obj) {
                switch (obj.event) {
                    case 'getCheckData':
                        var checkStatus = table.checkStatus(obj.config.id); //获取选中行状态
                        var data = checkStatus.data; //获取选中行数据
                        layer.alert(JSON.stringify(data));
                        break;
                    case 'add':
                        that.xAdminShowModal(that.title, that.url.add, null, null, function (msg) {
                            that.showModalInit(null);
                        });
                }
                ;
            });
            //监听行工具事件
            table.on("tool(" + that.id + "_table)", function (obj) {
                var data = obj.data;
                //console.log(obj)
                if (obj.event === 'del') {
                    layer.confirm('真的删除行么', function (index) {
                        DM.post(that.url.delete + "/" + obj.data.id, null, function (msg) {
                            layer.alert(msg.message);
                        });
                        layer.close(index);
                    });
                }
                else if (obj.event === 'edit') {
                    that.xAdminShowModal(that.title, that.url.add, null, null, function (msg) {
                        that.showModalInit(obj.data);
                    });
                }
            });
        });
    };
    ;
    BaseModel.prototype.reloadTable = function () {
        var that = this;
        layui.use('table', function () {
            var table = layui.table;
            var demoReload = $('#demoReload');
            //执行重载
            table.reload(that.id + "_reload", {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: that.params
            });
        });
    };
    ;
    BaseModel.prototype.showModalInit = function (obj) {
        var that = this;
        //const dom = $('#staff-add').parent();
        //dom.find('.layui-layer-btn0').attr('v-on:click', 'saveData');
        that.modalVue = new Vue({
            el: "#" + this.id + "_form",
            data: {
                obj: that.entity
            },
            mounted: function () {
                var thatV = this;
                if (obj != null) {
                    DM.get(that.url.getById + "/" + obj.id, null, function (msg) {
                        if (msg.success && msg.data != null) {
                            thatV.obj = msg.data;
                        }
                    });
                }
            },
            methods: {
                submitData: function () {
                    var thatV = this;
                    layui.use('form', function () {
                        var form = layui.form;
                        //各种基于事件的操作，下面会有进一步介绍
                        form.on('submit(submitData)', function (data) {
                            console.log(data.elem); //被执行事件的元素DOM对象，一般为button对象
                            console.log(data.form); //被执行提交的form对象，一般在存在form标签时才会返回
                            console.log(data.field); //当前容器的全部表单字段，名值对形式：{name: value}
                            thatV.saveData();
                            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
                        });
                    });
                },
                saveData: function () {
                    var thatV = this;
                    var url = that.url.create;
                    if (obj != null) {
                        url = that.url.update;
                    }
                    DM.post(url, thatV.obj, function (msg) {
                        layer.alert(msg.message);
                        that.reloadTable();
                    });
                }
            }
        });
    };
    ;
    BaseModel.prototype.xAdminShowModal = function (title, url, w, h, func) {
        var that = this;
        if (title == null || title == '') {
            title = false;
        }
        ;
        if (url == null || url == '') {
            url = "404.html";
        }
        ;
        if (w == null || w == '') {
            w = ($(window).width() * 0.5);
        }
        ;
        if (h == null || h == '') {
            h = ($(window).height() * 0.5);
        }
        ;
        DM.get(url, null, function (msg) {
            //页面层-自定义
            layer.open({
                id: 'staff-add',
                type: 1,
                area: [w + 'px', h + 'px'],
                fix: false,
                maxmin: true,
                shadeClose: true,
                shade: 0.4,
                title: title,
                content: msg,
                btn: ['保存', '取消'],
                yes: function (index, layero) {
                    layer.close(index);
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
        });
    };
    Object.defineProperty(BaseModel.prototype, "url", {
        get: function () {
            return this._url;
        },
        set: function (value) {
            this._url = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(BaseModel.prototype, "params", {
        get: function () {
            return this._params;
        },
        set: function (value) {
            this._params = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(BaseModel.prototype, "columns", {
        get: function () {
            return this._columns;
        },
        set: function (value) {
            this._columns = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(BaseModel.prototype, "id", {
        get: function () {
            return this._id;
        },
        set: function (value) {
            this._id = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(BaseModel.prototype, "entity", {
        get: function () {
            return this._entity;
        },
        set: function (value) {
            this._entity = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(BaseModel.prototype, "rowId", {
        get: function () {
            return this._rowId;
        },
        set: function (value) {
            this._rowId = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(BaseModel.prototype, "modalHide", {
        get: function () {
            return this._modalHide;
        },
        set: function (value) {
            this._modalHide = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(BaseModel.prototype, "rowName", {
        get: function () {
            return this._rowName;
        },
        set: function (value) {
            this._rowName = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(BaseModel.prototype, "modalVue", {
        get: function () {
            return this._modalVue;
        },
        set: function (value) {
            this._modalVue = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(BaseModel.prototype, "title", {
        get: function () {
            return this._title;
        },
        set: function (value) {
            this._title = value;
        },
        enumerable: true,
        configurable: true
    });
    return BaseModel;
}());
