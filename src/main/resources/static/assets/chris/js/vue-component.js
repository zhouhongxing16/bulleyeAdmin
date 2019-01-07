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
