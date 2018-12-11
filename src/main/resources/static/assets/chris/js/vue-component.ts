
Vue.component("left-menu",{
    template:`
    <div id="side-nav">
        <ul id="nav">
            <li v-for="(item,i) in data">
                    <a href="javascript:;" v-bind:_href="item.path+'?menuId='+item.id">
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
                    <a v-bind:_href="item.path+'?menuId='+item.id">
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