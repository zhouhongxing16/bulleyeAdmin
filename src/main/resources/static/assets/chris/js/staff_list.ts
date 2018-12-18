class StaffList extends BaseModel{
    id = 'staff';
    title:"员工信息";
    url = {
        listByPage: '/staff/listByPage',
        create: '/staff/create',
        update: '/staff/update',
        delete: '/staff/remove',
        view: '/staff/view',
        edit: '/staff/add',
        add: '/staff/add'
    };
    params: any = {
        type:null
    };

    columns = [[
        {type:'radio'},
        {field:'id', width:80, title: 'ID', sort: true},
        {field:'name', width:80, title: '姓名'},
        {field:'sex', width:80, title: '性别', sort: true},
        {field:'birthday', width:80, title: '城市'},
        {field:'serialNo', title: '签名', width: 80} ,
        {field:'experience', title: '积分', sort: true},
        {field:'score', title: '评分', sort: true},
        {field:'classify', title: '职业'},
        {field:'wealth', width:137, title: '财富', sort: true}
        ,{fixed: 'right', title:'操作', toolbar: `#${this.id}_optionBar`, width:150}
    ]];

    initPost(){

    }
}
const staffList = new StaffList();