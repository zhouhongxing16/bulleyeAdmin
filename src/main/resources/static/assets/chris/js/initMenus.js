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
        $.get(that.url.getCurrentMenus, function (msg) {
            if (msg.success) {
                that.initMenuVue(msg.data);
            }
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
                    layui.config({ base: '../../assets/global/' }).extend({ lau: 'lau/lau' }).use(['lau'], function () {
                        var lau = layui.lau, layer = layui.layer, $ = layui.$;
                        //监听事件,这个不一定要用lau-event,可以自己写
                        $(document).on('click', '[lau-event]', function () {
                            var _this = $(this);
                            switch (_this.attr('lau-event')) {
                                case "sideMenu0":
                                    lau.sideMenuChange(0);
                                    break;
                                case "sideMenu1":
                                    lau.sideMenuChange(1);
                                    break;
                                case "reload":
                                    lau.reload();
                                    break;
                            }
                        });
                    });
                }, 1000);
            }
        });
    };
    return InitMenus;
}());
var initMenus = new InitMenus();
