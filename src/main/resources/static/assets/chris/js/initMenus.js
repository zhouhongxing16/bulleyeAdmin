var InitMenus = /** @class */ (function () {
    function InitMenus() {
        this.url = {
            getCurrentMenus: "/menu/getMenusByAccountId",
        };
    }
    InitMenus.prototype.init = function () {
        this.initMenus();
    };
    ;
    InitMenus.prototype.initMenus = function () {
        var that = this;
        DM.get(that.url.getCurrentMenus, null, function (msg) {
            that.initMenuVue(msg.data);
        });
    };
    ;
    InitMenus.prototype.initMenuVue = function (data) {
        var that = this;
        that.menuVue = new Vue({
            el: "#menu_vue",
            data: {
                menuList: data,
                menuData: {
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
            mounted: function () {
                setTimeout(function () {
                }, 1000);
            }
        });
    };
    return InitMenus;
}());
var initMenus = new InitMenus();
