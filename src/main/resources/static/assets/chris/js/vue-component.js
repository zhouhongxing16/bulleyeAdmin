/*

Vue.component("left-menu",{
    template:`
    <div id="side-nav">
        <ul id="nav">
            <li v-for="(item,i) in data">
                    <a href="javascript:;" v-bind:_href="item.path" v-bind:id="item.id">
                        <i class="iconfont">&#xe6b8;</i>
                        <cite>{{item.name}}</cite>
                        <i v-if="item.children!=null" class="iconfont nav_right">&#xe697;</i>
                    </a>
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
            <ul class="sub-menu">
                <li v-for="(item,i) in data">
                    <a v-bind:_href="item.path" v-bind:id="item.id">
                        <i class="iconfont">&#xe6a7;</i>
                        <cite>{{item.name}}</cite>
                    </a>
                    <children-menu v-if="item.children!=null" v-bind:data="item.children"></children-menu>
                </li >
            </ul>`,
    props:{
        data: {
            type: Array,
            default: []
        }
    }
});
*/
Vue.component("left-menu", {
    template: "\n        <div class=\"layui-side-scroll\">\n            <ul class=\"layui-nav layui-nav-tree\" id=\"ul\" style=\"background-color:#20222a\">\n                    <!--\u8FD9\u91CC\u53EF\u4EE5\u5199\u83DC\u5355\u7ED3\u6784,\u4E5F\u53EF\u4EE5\u653E\u7A7A,\u4F7F\u7528\u5F02\u6B65\u52A0\u8F7D-->\n                    <span class=\"layui-nav-bar\" style=\"height: 0px; top: 0px; opacity: 0;\"></span>\n                    <li class=\"lau-nav-item\" v-for=\"(item,i) in data\">\n                        <a class=\"lau-nav-header\" v-bind:lau-href=\"item.path\" v-bind:id=\"item.id\"><i class=\"layui-icon layui-icon-auz\"></i><cite>{{item.name}}</cite></a>\n                        <children-menu v-if=\"item.children!=null\" v-bind:data=\"item.children\"></children-menu>\n                    </li>\n             </ul>\n        </div>",
    props: {
        data: {
            type: Array,
            default: []
        }
    }
});
Vue.component("children-menu", {
    template: "\n            <dl class=\"lau-nav-child\">\n                <dd v-for=\"(item,i) in data\">\n                    <a v-bind:lau-href=\"item.path\" v-bind:id=\"item.id\" lau-event=\"reload\"><i class=\"layui-icon\"></i><cite>{{item.name}}</cite></a>\n                    <children-menu v-if=\"item.children!=null\" v-bind:data=\"item.children\"></children-menu>\n                </dd>\n            </dl>",
    props: {
        data: {
            type: Array,
            default: []
        }
    }
});
