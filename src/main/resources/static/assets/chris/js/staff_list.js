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
            add: '/staff/add',
            getById: '/staff/getById'
        };
        _this.params = {
            type: "21111"
        };
        _this.entity = {
            obj: {
                name: null,
                genderId: null,
                email: null
            },
            data: {
                genderData: [{ id: '1', name: '男' }, { id: '0', name: '女' }]
            }
        };
        _this.columns = [[
                { type: 'radio' },
                { field: 'id', title: 'ID', sort: true, hide: true },
                { field: 'name', title: '姓名' },
                { field: 'genderId', title: '性别', sort: true },
                { field: 'mobile', title: '手机号' },
                { field: 'serialNo', title: '工号', sort: true },
                { field: 'email', title: '邮箱' },
                { field: 'identifyNo', title: '身份证号码' },
                { field: 'departmentName', title: '所属部门', sort: true },
                { field: 'positionName', title: '职务', sort: true },
                { fixed: 'right', title: '操作', toolbar: "#" + _this.id + "_optionBar", width: 150 }
            ]];
        return _this;
    }
    StaffList.prototype.initPost = function () {
    };
    return StaffList;
}(BaseModel));
var staffList = new StaffList();
