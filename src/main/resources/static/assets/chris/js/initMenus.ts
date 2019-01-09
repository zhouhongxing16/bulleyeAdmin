declare const $,Vue;
class InitMenus {
    url={
        getCurrentMenus:"/menu/getMenusByAccountId",
    };
    init(){
        this.initMenus();
    };
    menuVue:null;
    initMenus() {
        let that = this;
        DM.get(that.url.getCurrentMenus,null,function (msg) {
            that.initMenuVue(msg.data);
        });
    };
    initMenuVue(data){
        let that = this;
        that.menuVue = new Vue({
            el:"#menu_vue",
            data:{
                menuList:data,
                menuData:{
                    id: null,
                    pId: null,
                    icon: null,
                    code: null,
                    name: null,
                    path: null,
                    status: null,
                    sort: null,
                    created: null,
                    open: null,
                    checked: null,
                    rId: null,
                    children: [],
                },
            },
            mounted:function () {
                setTimeout(function () {
                },1000)
            }
        })
    }

}
let initMenus = new InitMenus();