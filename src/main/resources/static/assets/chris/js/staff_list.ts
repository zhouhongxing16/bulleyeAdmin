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
        add: '/staff/add',
        getById:'/staff/getById'
    };
    params: any = {
        type:"21111"
    };

    entity={
        obj:{
            name:null,
            genderId:null,
            email:null
        },
        data:{
            genderData:[{id:'1',name:'男'},{id:'0',name:'女'}]
        }

    };

    columns = [[
        {type:'radio'},
        {field:'id', title: 'ID', sort: true,hide:true},
        {field:'name',title: '姓名'},
        {field:'genderId',title: '性别', sort: true},
        {field:'mobile',  title: '手机号'},
        {field:'serialNo',  title: '工号', sort: true},
        {field:'email',  title: '邮箱'},
        {field:'identifyNo', title: '身份证号码'} ,
        {field:'departmentName', title: '所属部门', sort: true},
        {field:'positionName', title: '职务', sort: true},
        {fixed: 'right', title:'操作', toolbar: `#${this.id}_optionBar`, width:150}
    ]];

    initPost(){

    }
}
const staffList = new StaffList();