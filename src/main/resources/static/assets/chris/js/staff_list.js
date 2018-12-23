var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
        return extendStatics(d, b);
    }
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
var StaffList = /** @class */ (function (_super) {
    __extends(StaffList, _super);
    function StaffList() {
        var _this = _super !== null && _super.apply(this, arguments) || this;
        _this.id = 'staff';
        _this.url = {
            listByPage: '/staff/listByPage',
            create: '/staff/create',
            update: '/staff/update',
            delete: '/staff/remove',
            view: '/staff/view',
            edit: '/staff/add',
            add: '/staff/add'
        };
        _this.params = {
            type: null
        };
        _this.entity = {
            name: null,
            genderId: null,
            email: null
        };
        _this.columns = [[
                { type: 'radio' },
                { field: 'id', width: 80, title: 'ID', sort: true },
                { field: 'name', width: 80, title: '姓名' },
                { field: 'sex', width: 80, title: '性别', sort: true },
                { field: 'birthday', width: 80, title: '城市' },
                { field: 'serialNo', title: '签名', width: 80 },
                { field: 'experience', title: '积分', sort: true },
                { field: 'score', title: '评分', sort: true },
                { field: 'classify', title: '职业' },
                { field: 'wealth', width: 137, title: '财富', sort: true },
                { fixed: 'right', title: '操作', toolbar: "#" + _this.id + "_optionBar", width: 150 }
            ]];
        return _this;
    }
    StaffList.prototype.initPost = function () {
    };
    return StaffList;
}(BaseModel));
var staffList = new StaffList();
