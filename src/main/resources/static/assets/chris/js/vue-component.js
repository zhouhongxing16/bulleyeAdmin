Vue.component("left-menu", {
    template: "\n    <div id=\"side-nav\">\n        <ul id=\"nav\">\n            <li v-for=\"(item,i) in data\">\n                    <a href=\"javascript:;\" v-bind:_href=\"item.path\" v-bind:id=\"item.id\">\n                        <i class=\"iconfont\">&#xe6b8;</i>\n                        <cite>{{item.name}}</cite>\n                        <i v-if=\"item.children!=null\" class=\"iconfont nav_right\">&#xe697;</i>\n                    </a>\n                    <children-menu v-if=\"item.children!=null\" v-bind:data=\"item.children\"></children-menu>\n             </li>\n        </ul>\n    </div>",
    props: {
        data: {
            type: Array,
            default: []
        }
    }
});
Vue.component("children-menu", {
    template: "\n            <ul class=\"sub-menu\">\n                <li v-for=\"(item,i) in data\">\n                    <a v-bind:_href=\"item.path\" v-bind:id=\"item.id\">\n                        <i class=\"iconfont\">&#xe6a7;</i>\n                        <cite>{{item.name}}</cite>\n                    </a>\n                    <children-menu v-if=\"item.children!=null\" v-bind:data=\"item.children\"></children-menu>\n                </li >\n            </ul>",
    props: {
        data: {
            type: Array,
            default: []
        }
    }
});
/*
Vue.component("left-menu",{
    template:`
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree" id="ul" style="background-color:#20222a">
                    <!--这里可以写菜单结构,也可以放空,使用异步加载-->
                    <span class="layui-nav-bar" style="height: 0px; top: 0px; opacity: 0;"></span>
                    <li class="lau-nav-item" v-for="(item,i) in data">
                        <a class="lau-nav-header" v-bind:lau-href="item.path" v-bind:id="item.id"><i class="layui-icon layui-icon-auz"></i><cite>{{item.name}}</cite></a>
                        <children-menu v-if="item.children!=null" v-bind:data="item.children"></children-menu>
                    </li>
             </ul>
        </div>`,
    props:{
        data: {
            type: Array,
            default: []
        }
    }
});
Vue.component("children-menu",{
    template:`
            <dl class="lau-nav-child">
                <dd v-for="(item,i) in data">
                    <a v-bind:lau-href="item.path" v-bind:id="item.id" lau-event="reload"><i class="layui-icon"></i><cite>{{item.name}}</cite></a>
                    <children-menu v-if="item.children!=null" v-bind:data="item.children"></children-menu>
                </dd>
            </dl>`,
    props:{
        data: {
            type: Array,
            default: []
        }
    }
});*/ 
