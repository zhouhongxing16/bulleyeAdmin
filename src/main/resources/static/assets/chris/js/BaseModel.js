var BaseModel = /** @class */ (function () {
    function BaseModel() {
        this._params = {};
        this._entity = {};
        this._rowId = 'id';
        this._modalHide = true;
        this._rowName = 'name';
    }
    BaseModel.prototype.init = function () {
        this.initPost(this);
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
                        DM.xAdminShowModal(that.title, that.url.add, null, null, function (msg) {
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
                        DM.post(that.url.delete, { id: obj.data.id }, function (msg) {
                            if (msg.success) {
                                layer.alert(msg.message);
                            }
                            else {
                                layer.alert(msg.message);
                            }
                        }, false);
                        // obj.del();
                        layer.close(index);
                    });
                }
                else if (obj.event === 'edit') {
                    DM.xAdminShowModal(that.title, that.url.add, null, null, function (msg) {
                        that.showModalInit(obj.data);
                    });
                }
            });
        });
    };
    ;
    BaseModel.prototype.showModalInit = function (obj) {
        var that = this;
        new Vue({
            el: "#" + this.id + "_form",
            data: {
                obj: that.entity
            },
            mounted: function () {
                var thatV = this;
                if (obj != null) {
                    DM.get(that._url.getById, { id: obj.id }, function (msg) {
                        if (msg.success) {
                            thatV.obj = msg.data;
                        }
                    }, false);
                }
            }
        });
    };
    ;
    BaseModel.prototype.viewInit = function (that, data, id) {
    };
    BaseModel.prototype.initPost = function (that) {
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
